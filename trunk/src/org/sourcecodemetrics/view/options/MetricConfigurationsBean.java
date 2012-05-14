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

package org.sourcecodemetrics.view.options;

import java.util.List;
import org.sourcecodemetrics.view.options.limitations.MetricConfiguration;
import org.sourcecodemetrics.view.options.limitations.MetricConfigurations;

/**
 *
 * @author Krystian
 */
public class MetricConfigurationsBean {
    public List<MetricConfiguration> getMetricConfigurations() {
        return MetricConfigurations.getMc().getMetricConfigurations();
    }
}
