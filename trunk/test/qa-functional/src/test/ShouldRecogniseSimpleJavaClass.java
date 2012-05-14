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
import java.util.Iterator;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Krystian Warzocha
 */
public class ShouldRecogniseSimpleJavaClass extends BasicTestCase {

    public ShouldRecogniseSimpleJavaClass(String testName) {
        super(testName);
    }

    /**
     * Creates suite from particular test cases. 
     * You can define order of testcases here. 
     */
    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(ShouldRecogniseSimpleJavaClass.class);
        testConfig.addTest(ShouldRecogniseSimpleJavaClass.class, "testShouldComputeCyclomaticComplexity");
        return NbModuleSuite.create(testConfig);
    }

    public void testShouldRecogniseSimpleJavaClass() throws IOException, BuildException, MeasurementException {

        // given the java file
        FileObject testPackage = src.createFolder(PACKAGE_NAME);
        OutputStream outputStream = testPackage.createAndOpen(FILE_NAME);
        PrintStream testJavaStream = new PrintStream(outputStream);
        testJavaStream.print(
                "package testPackage;\n" 
                + "import java.awt.Frame;\n"
                + "public class " + CLASS_NAME + " extends Frame {\n"
                + "}");
        testJavaStream.close();

        // when asked to measure the metrics
        IProject resultProject = measurer.measure(project);

        // then 
        assertEquals("Project name is wrong", PROJECT_NAME, resultProject.getName());
        assertNotNull("The returned set of packages is null", resultProject.getPackages());
        assertEquals("There should be only one package", 2, resultProject.getPackages().size());

        IPackage package1 = null;
        Iterator<IPackage> it1 = resultProject.getPackages().iterator();
        while (it1.hasNext()) {
            IPackage pkg = it1.next();
            if (PACKAGE_NAME.equals(pkg.getName())) {
                package1 = pkg;
                break;
            }
        }
        assertNotNull("The package is null", package1);
        assertEquals("The name of the package is wrong", PACKAGE_NAME, package1.getName());
        assertEquals("Number of source files is wrong", 1, package1.getSourceFiles().size());

        ISourceFile sourceFile1 = package1.getSourceFiles().iterator().next();
        assertNotNull("Source file is null", sourceFile1);
        assertEquals("Name of the source file is wrong", FILE_NAME, sourceFile1.getName());
        assertEquals("Number of classes is wrong", 1, sourceFile1.getClasses().size());

        IClass class1 = sourceFile1.getClasses().iterator().next();
        assertNotNull("Class is null", class1);
        assertEquals("Class name is wrong", CLASS_NAME, class1.getName());
    }
}
