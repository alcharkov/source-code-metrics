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

package org.sourcecodemetrics.report.api;

import org.sourcecodemetrics.measurer.api.IProject;

/**
 * Interface allowing the generation of the Excel report.
 * @author Krystian Warzocha
 */
public interface IReportGenerator {
    
    /**
     * Generates report.
     * @param project Tree of metrics.
     * @param file File where the report shall be generated.
     */
    public void generateReport(IProject project, String filePath);
    
}
