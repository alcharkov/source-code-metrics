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
public class ShouldComputeD extends BasicTestCase {

    public ShouldComputeD(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(ShouldComputeD.class);
        testConfig.addTest(ShouldComputeD.class, "testShouldComputeD");
        return NbModuleSuite.create(testConfig);
    }

    public void testShouldComputeD() throws IOException, BuildException, MeasurementException {
        // given the java package with name package0 with EC=2 and AC=2
        FileObject measuredPackage = src.createFolder("package0");
        OutputStream outputStream = measuredPackage.createAndOpen("package0class1.java");
        PrintStream package0class1 = new PrintStream(outputStream);
        package0class1.println("package package0;");
        package0class1.println("import package1.package1class1;");
        package0class1.println("public class package0class1 {}");
        package0class1.close();

        outputStream = measuredPackage.createAndOpen("package0class2.java");
        PrintStream package0class2 = new PrintStream(outputStream);
        package0class2.println("package package0;");
        package0class2.println("import package1.package1class1;");
        package0class2.println("import package1.package1class3;");
        package0class2.println("public class package0class2 {}");
        package0class2.close();

        outputStream = measuredPackage.createAndOpen("package0class3.java");
        PrintStream package0class3 = new PrintStream(outputStream);
        package0class3.println("package package0;");
        package0class3.println("import package1.package1class3;");
        package0class3.println("import package2.package2class1;");
        package0class3.println("public class package0class3 {}");
        package0class3.close();

        outputStream = measuredPackage.createAndOpen("package0class4.java");
        PrintStream package0class4 = new PrintStream(outputStream);
        package0class4.println("package package0;");
        package0class4.println("public abstract class package0class4 {}");
        package0class4.close();

        outputStream = measuredPackage.createAndOpen("package0class5.java");
        PrintStream package0class5 = new PrintStream(outputStream);
        package0class5.println("package package0;");
        package0class5.println("public class package0class5 {}");
        package0class5.close();

        outputStream = measuredPackage.createAndOpen("package0class6.java");
        PrintStream package0class6 = new PrintStream(outputStream);
        package0class6.println("package measuredPackage;");
        package0class6.println("public class package0class6 {}");
        package0class6.close();

        // package1
        FileObject testPackage = src.createFolder("package1");
        outputStream = testPackage.createAndOpen("package1class1.java");
        PrintStream package1class1 = new PrintStream(outputStream);
        package1class1.println("package package1;");
        package1class1.println("public class package1class1 {}");
        package1class1.close();

        outputStream = testPackage.createAndOpen("package1class2.java");
        PrintStream package1class2 = new PrintStream(outputStream);
        package1class2.println("package package1;");
        package1class2.println("public class package1class2 {}");
        package1class2.close();

        outputStream = testPackage.createAndOpen("package1class3.java");
        PrintStream package1class3 = new PrintStream(outputStream);
        package1class3.println("package package1;");
        package1class3.println("public class package1class3 {}");
        package1class3.close();

        // package2
        FileObject package2 = src.createFolder("package2");
        outputStream = package2.createAndOpen("package2class1.java");
        PrintStream package2class1 = new PrintStream(outputStream);
        package2class1.println("package package2;");
        package2class1.println("public class package2class1 {}");
        package2class1.close();

        outputStream = package2.createAndOpen("package2class2.java");
        PrintStream package2class2 = new PrintStream(outputStream);
        package2class2.println("package package2;");
        package2class2.println("import package0.package0class6;");
        package2class2.println("public class package2class2 {}");
        package2class2.close();

        // package3
        FileObject package3 = src.createFolder("package3");
        outputStream = package3.createAndOpen("package3class1.java");
        PrintStream package3class1 = new PrintStream(outputStream);
        package3class1.println("package package3;");
        package3class1.println("public class package3class1 {}");
        package3class1.close();

        outputStream = package3.createAndOpen("package3class2.java");
        PrintStream package3class2 = new PrintStream(outputStream);
        package3class2.println("package package3;");
        package3class2.println("import package0.package0class5;");
        package3class2.println("import package0.package0class6;");
        package3class2.println("public class package3class2 {}");
        package3class2.close();

        outputStream = package3.createAndOpen("package3class3.java");
        PrintStream package3class3 = new PrintStream(outputStream);
        package3class3.println("package package3;");
        package3class3.println("import package0.package0class6;");
        package3class3.println("public class package3class3 {}");
        package3class3.close();

        // when measuring the whole project
        IProject result = measurer.measure(project);

        // then the measured metrics should be equal to the real values
        assertNotNull("Result is null", result);

        Collection<IPackage> packages = result.getPackages();
        assertNotNull("The list of packages is null.", packages);
        assertEquals("Number of packages is not correct", 5, packages.size());

        Iterator<IPackage> it = packages.iterator();
        IPackage ipackage0 = null;
        IPackage ipackage1 = null;
        IPackage ipackage2 = null;
        IPackage ipackage3 = null;

        while (it.hasNext()) {
            IPackage p = it.next();
            if ("package0".equals(p.getName())) {
                ipackage0 = p;
            } else if ("package1".equals(p.getName())) {
                ipackage1 = p;
            } else if ("package2".equals(p.getName())) {
                ipackage2 = p;
            } else if ("package3".equals(p.getName())) {
                ipackage3 = p;
            }
        }

        assertNotNull("package0 is null", ipackage0);
        assertNotNull("package1 is null", ipackage1);
        assertNotNull("package2 is null", ipackage2);
        assertNotNull("package3 is null", ipackage3);

        assertEquals("Efferent coupling is wrong", Integer.valueOf(2), ipackage0.getEC());
        assertEquals("Afferent coupling is wrong", Integer.valueOf(2), ipackage0.getAC());
        assertEquals("Instability is wrong", Double.valueOf(0.5), ipackage0.getI());
        assertEquals("Distance is wrong", Math.abs(Double.valueOf((1.0/6.0+0.5-1.0)/Math.sqrt(2.0))), ipackage0.getD());
    }
}
