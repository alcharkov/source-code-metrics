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

package org.sourcecodemetrics.measurer.api;

import java.io.IOException;
import java.util.logging.Level;
import org.openide.filesystems.FileObject;

/**
 * Allows to measure the metrics of the whole java project.
 * @author Krystian Warzocha
 */
public interface IMeasurer {

    /**
     * Measures all of the metrics of the given project. The call blocks until
     * all of the metrics are not measured.
     * @param project Directory containing the java project.
     * @throws MeasurementException Thrown when there was a problem during
     * performing measurements.
     * @throws BuildException Thrown when there was a problem with building of the 
     * tree of objects.
     * @return The tree of objects with the metrics values' in a hierarchical structure.
     */
    IProject measure(FileObject project) throws MeasurementException, BuildException, IOException;
    
    /**
     * Initlizes the Measurer module.
     */
    void initilize();
    
    /**
     * Sets the level of logs of the Measurer module.
     * @param level The logging level.
     */
    void setLogLevel(Level level);
}
