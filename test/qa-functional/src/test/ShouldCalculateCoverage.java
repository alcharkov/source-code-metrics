/**
 * This file is part of SourceCodeMetrics project.
 *
 * Copyright (C) 2012 Krystian Warzocha
 *
 * SourceCodeMetrics is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * SourceCodeMetrics is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * SourceCodeMetrics. If not, see http://www.gnu.org/licenses/.
 */
package test;

import org.sourcecodemetrics.measurer.api.BuildException;
import org.sourcecodemetrics.measurer.api.IClass;
import org.sourcecodemetrics.measurer.api.IPackage;
import org.sourcecodemetrics.measurer.api.IProject;
import org.sourcecodemetrics.measurer.api.ISourceFile;
import org.sourcecodemetrics.measurer.api.MeasurementException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Krystian Warzocha
 */
public class ShouldCalculateCoverage extends BasicTestCase {

    public ShouldCalculateCoverage(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(ShouldCalculateCoverage.class);
        testConfig.addTest(ShouldCalculateCoverage.class, "testShouldCalculateCoverage");
        return NbModuleSuite.create(testConfig);
    }

    public void testShouldCalculateCoverage() throws IOException, MeasurementException, BuildException {
        // given the package and its tests
        FileObject testsPackage = test.createFolder("testsPackage");
        OutputStream outputStream = testsPackage.createAndOpen("Tests.java");
        PrintStream testFile = new PrintStream(outputStream);
        testFile.println("package testsPackage;");
        testFile.println("import org.junit.*;");
        testFile.println("import static org.junit.Assert.*;");
        testFile.println("import measuredPackage.Class1;");
        testFile.println("import measuredPackage.Class2;");
        testFile.println("import measuredPackage.Class3;");
        testFile.println("import measuredPackage.Class4;");
        testFile.println("public class Tests {");
        testFile.println(" @Test");
        testFile.println(" public void testCase1() {");
        testFile.println("  Class1 c = new Class1();");
        testFile.println("  Class2 c2 = new Class2();");
        testFile.println("  c.method1(c2, 1);");
        testFile.println("  c.method2();");
        testFile.println("  c.method3();");
        testFile.println("  assertEquals(1, 2);");
        testFile.println(" }");
        testFile.println(" @Test");
        testFile.println(" public void testCase2() {");
        testFile.println("  Class2 c = new Class2();");
        testFile.println("  c.method();");
        testFile.println("  assertEquals(1, 2);");
        testFile.println(" }");
        testFile.println(" @Test");
        testFile.println(" public void testCase3() {");
        testFile.println("  Class3 c = new Class3();");
        testFile.println("  Class3.method();");
        testFile.println("  assertEquals(1, 2);");
        testFile.println(" }");
        testFile.println(" @Test");
        testFile.println(" public void testCase4() {");
        testFile.println("  Class4.method();");
        testFile.println(" }");
        testFile.println("}");
        testFile.close();

        FileObject measuredPackage = src.createFolder("measuredPackage");
        outputStream = measuredPackage.createAndOpen("Class1.java");
        PrintStream class1File = new PrintStream(outputStream);
        class1File.println("package measuredPackage;");
        class1File.println("import measuredPackage.Class2;");
        class1File.println("public class Class1 {");
        class1File.println(" public void method1(Class2 c2, Integer a) {}");
        class1File.println(" public void method2() {}");
        class1File.println(" public void method3() {}");
        class1File.println(" public void method4() {}");
        class1File.println(" public void method5() {}");
        class1File.println("}");
        class1File.close();

        outputStream = measuredPackage.createAndOpen("Class2.java");
        PrintStream class2File = new PrintStream(outputStream);
        class2File.println("package measuredPackage;");
        class2File.println("public class Class2 {");
        class2File.println(" public void method() {}");
        class2File.println("}");
        class2File.close();

        outputStream = measuredPackage.createAndOpen("Class3.java");
        PrintStream class3File = new PrintStream(outputStream);
        class3File.println("package measuredPackage;");
        class3File.println("public class Class3 {");
        class2File.println(" public static void method() {}");
        class3File.println("}");
        class3File.close();

        outputStream = measuredPackage.createAndOpen("Class4.java");
        PrintStream class4File = new PrintStream(outputStream);
        class4File.println("package measuredPackage;");
        class4File.println("public class Class4 {");
        class2File.println(" public static void method() {}");
        class4File.println("}");
        class4File.close();

        outputStream = measuredPackage.createAndOpen("Class5.java");
        PrintStream class5File = new PrintStream(outputStream);
        class5File.println("package measuredPackage;");
        class5File.println("public class Class5 {");
        class2File.println(" public void method() {}");
        class5File.println("}");
        class5File.close();

        // when commanded to measure the whole project
        IProject result = measurer.measure(project);

        // then the measured metrics should be equal to the real values
        assertNotNull("Result is null", result);

        Collection<IPackage> packages = result.getPackages();
        assertNotNull("The list of packages is null.", packages);
        assertEquals("Number of packages is not correct", 4, packages.size());

        IPackage tPkg = null;
        IPackage mPkg = null;
        Iterator<IPackage> it = packages.iterator();
        while (it.hasNext()) {
            IPackage pkg = it.next();
            if ("testsPackage".equals(pkg.getName())) {
                tPkg = pkg;
            } else if ("measuredPackage".equals(pkg.getName())) {
                mPkg = pkg;
            }
        }

        assertNotNull("testsPackage is null!", tPkg);
        assertNotNull("measuredPackage is null!", mPkg);

        Collection<ISourceFile> sources = mPkg.getSourceFiles();
        assertNotNull("Sources collections is null", sources);
        assertEquals("Number of sources is wrong", 5, sources.size());

        ISourceFile sourceFile1 = null;
        ISourceFile sourceFile2 = null;
        ISourceFile sourceFile3 = null;
        ISourceFile sourceFile4 = null;
        ISourceFile sourceFile5 = null;
        Iterator<ISourceFile> sfIt = sources.iterator();
        while (sfIt.hasNext()) {
            ISourceFile sf = sfIt.next();
            if ("Class1.java".equals(sf.getName())) {
                sourceFile1 = sf;
            } else if ("Class2.java".equals(sf.getName())) {
                sourceFile2 = sf;
            }
            if ("Class3.java".equals(sf.getName())) {
                sourceFile3 = sf;
            }
            if ("Class4.java".equals(sf.getName())) {
                sourceFile4 = sf;
            }
            if ("Class5.java".equals(sf.getName())) {
                sourceFile5 = sf;
            }
        }

        assertNotNull("Source Class1.java file is null", sourceFile1);
        assertNotNull("Source Class2.java file is null", sourceFile2);
        assertNotNull("Source Class3.java file is null", sourceFile3);
        assertNotNull("Source Class4.java file is null", sourceFile4);
        assertNotNull("Source Class5.java file is null", sourceFile5);

        Collection<IClass> classes1 = sourceFile1.getClasses();
        assertNotNull("Collection of classes 1 is null", classes1);
        assertEquals("Number of classes 1 is wrong", 1, classes1.size());
        IClass class1 = classes1.iterator().next();
        assertNotNull("class1 is null", class1);

        Collection<IClass> classes2 = sourceFile2.getClasses();
        assertNotNull("Collection of classes 2 is null", classes2);
        assertEquals("Number of classes 2 is wrong", 1, classes2.size());
        IClass class2 = classes2.iterator().next();
        assertNotNull("class2 is null", class2);

        Collection<IClass> classes3 = sourceFile3.getClasses();
        assertNotNull("Collection of classes 3 is null", classes3);
        assertEquals("Number of classes 3 is wrong", 1, classes3.size());
        IClass class3 = classes3.iterator().next();
        assertNotNull("class3 is null", class3);

        Collection<IClass> classes4 = sourceFile4.getClasses();
        assertNotNull("Collection of classes 4 is null", classes4);
        assertEquals("Number of classes 4 is wrong", 1, classes4.size());
        IClass class4 = classes4.iterator().next();
        assertNotNull("class4 is null", class4);

        Collection<IClass> classes5 = sourceFile5.getClasses();
        assertNotNull("Collection of classes 5 is null", classes5);
        assertEquals("Number of classes 5 is wrong", 1, classes5.size());
        IClass class5 = classes5.iterator().next();
        assertNotNull("class5 is null", class5);

        assertEquals("Number of test methods for Class1 is wrong", Integer.valueOf(1), class1.getNTM());
        assertTrue("NAK of Class1 is wrong", 55 < class1.getNAK() && class1.getNAK() < 56);
        assertEquals("Number of test methods for Class2 is wrong", Integer.valueOf(2), class2.getNTM());
        assertEquals("NAK of Class2 is wrong", Double.valueOf(375.0), class2.getNAK());
        assertEquals("Number of test methods for Class3 is wrong", Integer.valueOf(1), class3.getNTM());
        assertTrue("NAK of Class3 is wrong", 333 < class3.getNAK() && class3.getNAK() < 334);
        assertEquals("Number of test methods for Class4 is wrong", Integer.valueOf(1), class4.getNTM());
        assertEquals("NAK of Class4 is wrong", Double.valueOf(0), class4.getNAK());
        assertEquals("Number of test methods for Class5 is wrong", Integer.valueOf(0), class5.getNTM());
        assertEquals("NAK of Class5 is wrong", Double.valueOf(0), class5.getNAK());
        assertEquals("Package coverage is not correct", Double.valueOf(0.8), mPkg.getC());

        assertTrue("Average NAK for package measuredPackage is wrong", 152 < mPkg.getClassNAKAvg() && mPkg.getClassNAKAvg() < 153);
        assertTrue("Average NAK for project is wrong: " + result.getClassNAKAvg(), 152 < result.getClassNAKAvg() && result.getClassNAKAvg() < 153);
    }
}
