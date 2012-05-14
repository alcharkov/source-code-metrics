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

import java.util.Collection;

/**
 * Represents the java source file. The source file may contain multiple classes.
 * @author Krystian Warzocha.
 */
public interface ISourceFile {

    /**
     * Returns the name of the java source file.
     * @return Name of the java source file.
     */
    String getName();

    /**
     * Returns the classes that are in this source file.
     * @return List of classes of this source file.
     */
    Collection<IClass> getClasses();

    /**
     * Returns number of lines of code in this source file.
     * @return Lines of code in this source file.
     */
    Integer getLOC();

    /**
     * Rerutns number of lines of comments in this source file.
     * @return 
     */
    Integer getLOCm();
}
