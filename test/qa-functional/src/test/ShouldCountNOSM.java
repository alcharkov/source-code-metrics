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
 * @author Krystian
 */
public class ShouldCountNOSM extends BasicTestCase {

    public ShouldCountNOSM(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(ShouldCountNOSM.class);
        testConfig.addTest(ShouldCountNOSM.class, "testShouldCountNOSM");
        return NbModuleSuite.create(testConfig);
    }
    
    public void testShouldCountNOSM() throws IOException, MeasurementException, BuildException {
        
        // given the java file with three methods static methods and one normal one
        FileObject testPackage = src.createFolder(PACKAGE_NAME);
        OutputStream outputStream = testPackage.createAndOpen(FILE_NAME);
        PrintStream testJavaStream = new PrintStream(outputStream);
        testJavaStream.println("package " + PACKAGE_NAME + ";");
        testJavaStream.println("public class " + CLASS_NAME + "{");
        testJavaStream.println("public static void " + METHOD_NAME + "(int a, String b, double c) {}");
        testJavaStream.println("public static void " + METHOD_NAME_2 + "(int param) {}");
        testJavaStream.println("public static void " + METHOD_NAME_3 + "(String param) {}");
        testJavaStream.println("public void " + METHOD_NAME_4 + "(String param) {}");
        testJavaStream.println("}");
        testJavaStream.close();
        
        // when measure all of the metrics for the whole project
        IProject result = measurer.measure(project);
        
        // then the counted Number Of Static Methods should be 3
        assertNotNull("Result is null", result);

        Collection<IPackage> packages = result.getPackages();
        assertNotNull("The list of packages is null.", packages);
        assertEquals("Number of packages is not correct", 2, packages.size());

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
        
        assertEquals("Number of methods is not correct", Integer.valueOf(3), class1.getNOSM());
    }
    
    private static final String METHOD_NAME_2 = "methodUnderTest2";
    private static final String METHOD_NAME_3 = "methodUnderTest3";
    private static final String METHOD_NAME_4 = "methodUnderTest4";
}
