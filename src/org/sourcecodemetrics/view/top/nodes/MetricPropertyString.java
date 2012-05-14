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
import org.openide.nodes.PropertySupport;

/**
 *
 * @author Krystian
 */
public class MetricPropertyString extends PropertySupport.ReadOnly<String> implements TooltipProvider {

    /**
     * Constructor.
     * @param name Name of the property.
     */
    public MetricPropertyString(String name, String value, String tooltip) {
        super(name, String.class, name, name);
        this.name = name;
        this.value = value;
        this.tooltip = tooltip;
    }

    @Override
    public String getValue() throws IllegalAccessException, InvocationTargetException {
        return value;
    }

    @Override
    public String getTooltip() {
        return tooltip;
    }

    @Override
    public String toString() {
        return value;
    }
    private String value;
    private String tooltip;
    private String name;
}
