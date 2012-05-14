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
import org.sourcecodemetrics.measurer.api.IMethod;
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
public class ShouldCountLOCm extends BasicTestCase {

    public ShouldCountLOCm(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(ShouldCountLOCm.class);
        testConfig.addTest(ShouldCountLOCm.class, "testShouldCountLOCm");
        return NbModuleSuite.create(testConfig);
    }

    public void testShouldCountLOCm() throws IOException, MeasurementException, BuildException {
        // given the java file with 13 lines of comments
        FileObject testPackage = src.createFolder(PACKAGE_NAME);
        OutputStream outputStream = testPackage.createAndOpen(FILE_NAME);
        PrintStream testJavaStream = new PrintStream(outputStream);
        testJavaStream.println("package " + PACKAGE_NAME + ";");
        testJavaStream.println("/**");
        testJavaStream.println(" * This is a sample class.");
        testJavaStream.println(" * @author Krystian Warzocha");
        testJavaStream.println(" */");
        testJavaStream.println("public class SampleClass {");
        testJavaStream.println(" /**");
        testJavaStream.println("  * This is method m1.");
        testJavaStream.println("  * @param a Parameter a");
        testJavaStream.println("  * @param b Parameter b");
        testJavaStream.println("  * @param c Parameter c");
        testJavaStream.println("  * @return Some value.");
        testJavaStream.println("  */");
        testJavaStream.println(" public int m1(int a, String b, double c) {");
        testJavaStream.println("  // comparison of a");
        testJavaStream.println("  if(a == 1) {");
        testJavaStream.println("   if(c == 0.0) {");
        testJavaStream.println("    return 0;");
        testJavaStream.println("   }");
        testJavaStream.println("   // else statement");
        testJavaStream.println("  } else if(c == 1.0) {");
        testJavaStream.println("   return 2;");
        testJavaStream.println("  }");
        testJavaStream.println("  return 1;");
        testJavaStream.println(" }");
        testJavaStream.println(" public void m2() {");
        testJavaStream.println(" }");
        testJavaStream.println("}");
        testJavaStream.close();

        // when measure the whole project
        IProject result = measurer.measure(project);

        // then the mesured LOCm should be 13
        Collection<IPackage> packages = result.getPackages();
        assertNotNull("The list of packages is null.", packages);
        assertEquals("Number ofpackages is not correct", 2, packages.size());

        IPackage package1 = null;
        Iterator<IPackage> it1 = packages.iterator();
        while (it1.hasNext()) {
            IPackage pkg = it1.next();
            if (PACKAGE_NAME.equals(pkg.getName())) {
                package1 = pkg;
                break;
            }
        }

        assertNotNull("The package is null", package1);

        Collection<ISourceFile> sourceFiles = package1.getSourceFiles();
        assertNotNull("The set of source files is null", sourceFiles);
        assertEquals("Number of source files is not correct", 1, sourceFiles.size());

        ISourceFile sourceFile = sourceFiles.iterator().next();
        assertNotNull("The source file is null", sourceFile);

        Collection<IClass> classes = sourceFile.getClasses();
        assertNotNull("The set of classes is null", classes);
        assertEquals("The number of classes is not correct", 1, classes.size());

        IClass class1 = classes.iterator().next();
        assertNotNull("The class is null", class1);

        Collection<IMethod> methods = class1.getMethods();
        assertNotNull("The set of methods is null", methods);
        assertEquals("Number of methods is not correct", 2, methods.size());

        Iterator<IMethod> it = methods.iterator();
        IMethod m1 = null;
        while (it.hasNext()) {
            IMethod method = it.next();
            if ("m1".equals(method.getName())) {
                m1 = method;
            }
        }
        assertNotNull("Method m1 is null", m1);

        assertEquals("LOCm of source file is wrong", Integer.valueOf(13), sourceFile.getLOCm());
        assertEquals("LOC of source file is wrong", Integer.valueOf(28), sourceFile.getLOC());
        assertEquals("LOCm of class is wrong", Integer.valueOf(9), class1.getLOCm());
        assertEquals("LOC of class is wrong", Integer.valueOf(24), class1.getLOC());
        assertEquals("LOCm of method is wrong", Integer.valueOf(2), m1.getLOCm());
        assertEquals("LOC of method is wrong", Integer.valueOf(12), m1.getLOC());
    }
}
