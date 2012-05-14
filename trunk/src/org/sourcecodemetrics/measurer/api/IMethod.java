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

import org.openide.filesystems.FileObject;

/**
 * Represents a method. The method has the attributes:
 * <ul>
 * <li>name
 * <li>metrics
 * </ul>
 * @author Krystian Warzocha
 */
public interface IMethod {

    /**
     * Returns tha name of the method.
     * @return Name of the method.
     */
    String getName();
    
    /**
     * Returns the file object where the method has been defined.
     * @return File object where the method has been defined.
     */
    FileObject getFileObject();

    /**
     * Returns the cyclomatic complexity of the method.
     * @return Cyclomatic complecity of the method.
     */
    Integer getVG();
    
    /**
     * Returns the nested block depth of this method.
     * @return Nested Block Depth of the method.
     */
    Integer getNBD();
    
    /**
     * Returns the number of parameters of this method.
     * @return Number Of Parameters of this method.
     */
    Integer getNOP();
    
    /**
     * Returns the number of lines of code of this method.
     * @return Lines Of Code of this method.
     */
    Integer getLOC();
    
    /**
     * Returns the number of lines of comments in this method.
     * @return  Lines Of Comments in this method.
     */
    Integer getLOCm();
    
}
