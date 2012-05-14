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

package org.sourcecodemetrics.measurer.model.sources;

import org.sourcecodemetrics.measurer.model.classes.ClassImpl;

/**
 *
 * @author Krystian Warzocha
 */
class SourceMeasurer {

    SourceMeasurer(SourceFileImpl s) {
        this.s = s;
    }

    void measure() {
        // command all of the classes to perform next step of measurements
        for (ClassImpl ci : s.classes.values()) {
            ci.postMeasure();
        }
    }
    private SourceFileImpl s;
}
