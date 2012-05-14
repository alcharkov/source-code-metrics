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
import org.sourcecodemetrics.measurer.api.IPackage;
import org.sourcecodemetrics.measurer.api.IProject;
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
public class ShouldComputeAbstractness extends BasicTestCase {

    public ShouldComputeAbstractness(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(ShouldComputeAbstractness.class);
        testConfig.addTest(ShouldComputeAbstractness.class, "testShouldComputeAbstractness");
        return NbModuleSuite.create(testConfig);
    }

    public void testShouldComputeAbstractness() throws IOException, MeasurementException, BuildException {
        FileObject testPackage = src.createFolder(PACKAGE_NAME);
        OutputStream outputStream = testPackage.createAndOpen("Test1.java");
        PrintStream testJavaStream = new PrintStream(outputStream);
        testJavaStream.println("package " + PACKAGE_NAME + ";");
        testJavaStream.println("public class Test1 {");
        testJavaStream.println("}");
        testJavaStream.close();

        outputStream = testPackage.createAndOpen("Test2.java");
        testJavaStream = new PrintStream(outputStream);
        testJavaStream.println("package " + PACKAGE_NAME + ";");
        testJavaStream.println("public class Test2 {");
        testJavaStream.println("}");
        testJavaStream.close();

        outputStream = testPackage.createAndOpen("Test3.java");
        testJavaStream = new PrintStream(outputStream);
        testJavaStream.println("package " + PACKAGE_NAME + ";");
        testJavaStream.println("public class Test3 {");
        testJavaStream.println("}");
        testJavaStream.close();

        outputStream = testPackage.createAndOpen("Test4.java");
        testJavaStream = new PrintStream(outputStream);
        testJavaStream.println("package " + PACKAGE_NAME + ";");
        testJavaStream.println("public abstract class Test4 {");
        testJavaStream.println("}");
        testJavaStream.close();

        outputStream = testPackage.createAndOpen("Test5.java");
        testJavaStream = new PrintStream(outputStream);
        testJavaStream.println("package " + PACKAGE_NAME + ";");
        testJavaStream.println("public interface Test5 {");
        testJavaStream.println("}");
        testJavaStream.close();

        // when measure the whole project
        IProject result = measurer.measure(project);

        // then the measured metrics should be equal to the real values
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

        assertEquals("Abstractness is not correct", Double.valueOf(0.4), package1.getA());
    }
}
