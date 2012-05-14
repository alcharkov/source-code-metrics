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

package org.sourcecodemetrics.view.top.nodes;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import org.openide.nodes.PropertySupport;

/**
 *
 * @author Krystian
 */
public class MetricPropertyDouble extends PropertySupport.ReadOnly<Double> implements TooltipProvider {

    public MetricPropertyDouble(String name, Double value, String tooltip) {
        super(name, Double.class, name, name);

        // adjusting the precision
        String s = String.format(Locale.US, "%.2f", value);
        this.value = Double.valueOf(s);
        this.tooltip = tooltip;
    }

    @Override
    public Double getValue() throws IllegalAccessException, InvocationTargetException {
        return value;
    }

    @Override
    public String getTooltip() {
        return tooltip;
    }
    
    @Override
    public String toString() {
        if(value != null) {
            return value.toString();
        } else {
            return "";
        }
    }
    private Double value;
    private String tooltip;
}
