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

import org.sourcecodemetrics.measurer.MeasurerImpl;
import org.sourcecodemetrics.measurer.api.IMeasurer;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.logging.Level;
import org.netbeans.jellytools.JellyTestCase;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.LocalFileSystem;

/**
 *
 * @author Krystian Warzocha
 */
public class BasicTestCase extends JellyTestCase {

    public BasicTestCase(String testName) {
        super(testName);
    }

    @Override
    public void setUp() throws IOException, PropertyVetoException {
        // instantiation of the virtual file system held in memory
        clearWorkDir();
        LocalFileSystem fs = new LocalFileSystem();
        fs.setRootDirectory(getWorkDir());
        root = fs.getRoot();

        // creation of the project folder
        project = root.createFolder(PROJECT_NAME);

        // directory holding the java sources of the project
        src = project.createFolder("src");
        
        // directory holding the tests
        test = project.createFolder("test");

        // the measurer
        measurer = new MeasurerImpl();
        measurer.initilize();
        measurer.setLogLevel(Level.ALL);
    }

    /**
     * The root of the file system in memory.
     */
    protected FileObject root;
    /**
     * Project directory in the filesystem memory.
     */
    protected FileObject project;
    /**
     * Directory holding java sources in the filesystem.
     */
    protected FileObject src;
    /**
     * Directory storing the tests files.
     */
    protected FileObject test;
    /**
     * The reference to the main reference of the Measurer.
     */
    protected IMeasurer measurer;
    protected static final String PROJECT_NAME = "TestProject";
    protected static final String PACKAGE_NAME = "testPackage";
    protected static final String CLASS_NAME = "TestClass";
    protected static final String METHOD_NAME = "methodUnderTest";
    protected static final String FILE_NAME = "TestClass.java";
}
