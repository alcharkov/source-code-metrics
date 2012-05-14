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

package org.sourcecodemetrics.view.options.limitations;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Krystian
 */
public class MetricConfiguration {

    public MetricConfiguration() {
    }

    public MetricConfiguration(String symbol, String name, String scope, Double minimum, Double maximum) {
        this.symbol = symbol;
        this.name = name;
        this.scope = scope;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        Double old = this.maximum;
        this.maximum = maximum;
        pcs.firePropertyChange("maximum", old, this.maximum);
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        Double old = this.minimum;
        this.minimum = minimum;
        pcs.firePropertyChange("minimum", old, this.minimum);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    private String symbol;
    private String name;
    private String scope;
    private Double minimum;
    private Double maximum;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
}
