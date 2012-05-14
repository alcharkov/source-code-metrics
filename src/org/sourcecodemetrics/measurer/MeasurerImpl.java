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

package org.sourcecodemetrics.measurer;

import org.sourcecodemetrics.measurer.api.BuildException;
import org.sourcecodemetrics.measurer.api.IMeasurer;
import org.sourcecodemetrics.measurer.api.IProject;
import org.sourcecodemetrics.measurer.api.MeasurementException;
import org.sourcecodemetrics.measurer.log.LoggerConf;
import org.sourcecodemetrics.measurer.model.ProjectImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.filesystems.FileObject;

/**
 * Implements the service defined by the IMeasurer interface. 
 * @author Krystian Warzocha.
 */
public class MeasurerImpl implements IMeasurer {

    @Override
    public IProject measure(FileObject project) throws MeasurementException, BuildException, IOException {
        LoggerConf.reset(); // cleat the logging pane
        if (project == null) {
            throw new MeasurementException("Given top directory is null. Terminating measurements.");
        }
        return measureProject(project);
    }

    /**
     * Used for the measurement of metrics of the whole project.
     * @param project Directory of the whole of the project.
     * @return 
     */
    private IProject measureProject(FileObject project) throws MeasurementException, BuildException, IOException {
        logger.info("Measuring project: " + project.getName());
        projectImpl = new ProjectImpl(project);
        projectImpl.initlize();
        projectImpl.build();
        projectImpl.measure(); // performs measurements and builds the tree structure of the objects
        return projectImpl;
    }
    private static final Logger logger = Logger.getLogger(MeasurerImpl.class.getName());

    @Override
    public void initilize() {
        LoggerConf.setup(); // sets the default configuration of the 
    }

    @Override
    public void setLogLevel(Level level) {
        LoggerConf.setLevel(level); // sets the logging level
    }

    public static ProjectImpl getProjectImpl() {
        return projectImpl;
    }
    static private ProjectImpl projectImpl;
}
