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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.openide.util.NbPreferences;

/**
 *
 * @author Krystian
 */
public class MetricConfigurations {

    public MetricConfigurations() {
        metricConfigurations = new ArrayList<MetricConfiguration>();
        metricConfigurations.add(new MetricConfiguration("A", "Abstractness", "project", 0.0, 1.0));
        metricConfigurations.add(new MetricConfiguration("AC", "Average Afferent Coupling", "project", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("C", "Tests Coverage", "project", 0.1, 1.0));
        metricConfigurations.add(new MetricConfiguration("D", "Average Distance", "project", 0.0, 0.6));
        metricConfigurations.add(new MetricConfiguration("EC", "Average Efferent Coupling", "project", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("I", "Average Instability", "project", 0.0, 0.8));
        metricConfigurations.add(new MetricConfiguration("LOC", "Total Lines Of Code", "project", 0.0, 1000000.0));
        metricConfigurations.add(new MetricConfiguration("LOCm", "Total Lines of Comments", "project", 0.0, 1000000.0));
        metricConfigurations.add(new MetricConfiguration("NCP", "Average Number of Classes in Package", "project", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("NIP", "Average Number of Interfaces in Package", "project", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LCC", "Average Loose Class Coupling", "project", 0.1, 1.0));
        metricConfigurations.add(new MetricConfiguration("LOCM1", "Average Lack Of Cohesion in Methods 1", "project", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM2", "Average Lack Of Cohesion in Methods 2", "project", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM3", "Average Lack Of Cohesion in Methods 3", "project", 1.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM4", "Average Lack Of Cohesion in Methods 4", "project", 1.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM5", "Average Lack Of Cohesion in Methods 5", "project", 0.0, 0.8));
        metricConfigurations.add(new MetricConfiguration("NAK", "Average Number Of Asserts per KLOC", "project", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("NOC", "Average Number Of Children", "project", 0.0, 10.0));
        metricConfigurations.add(new MetricConfiguration("NOF", "Total Number Of Fields", "project", 0.0, 1000000.0));
        metricConfigurations.add(new MetricConfiguration("NOM", "Total Number Of Methods", "project", 0.0, 1000000.0));
        metricConfigurations.add(new MetricConfiguration("NOSF", "Total Number Of Static Fields", "project", 0.0, 1000000.0));
        metricConfigurations.add(new MetricConfiguration("NOSM", "Total Number Of Static Methods", "project", 0.0, 1000000.0));
        metricConfigurations.add(new MetricConfiguration("NTM", "Total Number of Test Methods", "project", 0.0, 500.0));
        metricConfigurations.add(new MetricConfiguration("WMC", "Total Weighted Methods Count", "project", 0.0, 1000000.0));
        metricConfigurations.add(new MetricConfiguration("TCC", "Average Tight Class Coupling", "project", 0.1, 1.0));
        metricConfigurations.add(new MetricConfiguration("NBD", "Average Nested Block Depth", "project", 0.0, 7.0));
        metricConfigurations.add(new MetricConfiguration("NOP", "Total Number Of Parameters", "project", 0.0, 1000000.0));
        metricConfigurations.add(new MetricConfiguration("VG", "Average McGabe's Cyclomatic Complexity", "project", 0.0, 7.0));

        metricConfigurations.add(new MetricConfiguration("A", "Abstractness", "package", 0.0, 1.0));
        metricConfigurations.add(new MetricConfiguration("AC", "Afferent Coupling", "package", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("C", "Tests Coverage", "package", 0.1, 1.0));
        metricConfigurations.add(new MetricConfiguration("D", "Distance", "package", 0.0, 0.6));
        metricConfigurations.add(new MetricConfiguration("EC", "Efferent Coupling", "package", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("I", "Instability", "package", 0.0, 0.8));
        metricConfigurations.add(new MetricConfiguration("LOC", "Lines Of Code", "package", 0.0, 10000.0));
        metricConfigurations.add(new MetricConfiguration("LOCm", "Lines of Comments", "package", 0.0, 10000.0));
        metricConfigurations.add(new MetricConfiguration("NCP", "Number of Classes in Package", "package", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("NIP", "Number of Interfaces in Package", "package", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LCC", "Average Loose Class Coupling", "package", 0.1, 1.0));
        metricConfigurations.add(new MetricConfiguration("LOC", "Total Lines Of Code", "package", 0.0, 10000.0));
        metricConfigurations.add(new MetricConfiguration("LOCm", "Total Lines of Comments", "package", 0.0, 10000.0));
        metricConfigurations.add(new MetricConfiguration("LOCM1", "Average Lack Of Cohesion in Methods 1", "package", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM2", "Average Lack Of Cohesion in Methods 2", "package", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM3", "Average Lack Of Cohesion in Methods 3", "package", 1.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM4", "Average Lack Of Cohesion in Methods 4", "package", 1.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM5", "Average Lack Of Cohesion in Methods 5", "package", 0.0, 0.8));
        metricConfigurations.add(new MetricConfiguration("NAK", "Average Number Of Asserts per KLOC", "package", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("NOC", "Average Number Of Children", "package", 0.0, 10.0));
        metricConfigurations.add(new MetricConfiguration("NOF", "Total Number Of Fields", "package", 0.0, 100000.0));
        metricConfigurations.add(new MetricConfiguration("NOM", "Total Number Of Methods", "package", 0.0, 100000.0));
        metricConfigurations.add(new MetricConfiguration("NOSF", "Total Number Of Static Fields", "package", 0.0, 100000.0));
        metricConfigurations.add(new MetricConfiguration("NOSM", "Total Number Of Static Methods", "package", 0.0, 100000.0));
        metricConfigurations.add(new MetricConfiguration("NTM", "Total Number of Test Methods", "package", 0.0, 100000.0));
        metricConfigurations.add(new MetricConfiguration("WMC", "Total Weighted Methods Count", "package", 0.0, 100000.0));
        metricConfigurations.add(new MetricConfiguration("TCC", "Average Tight Class Coupling", "package", 0.1, 1.0));
        metricConfigurations.add(new MetricConfiguration("NBD", "Average Nestted Block Depth", "package", 0.0, 7.0));
        metricConfigurations.add(new MetricConfiguration("NOP", "Total Number Of Parameters", "package", 0.0, 100000.0));
        metricConfigurations.add(new MetricConfiguration("VG", "Average McGabe's Cyclomatic Complexity", "package", 0.0, 7.0));

        metricConfigurations.add(new MetricConfiguration("LCC", "Loose Class Coupling", "class", 0.2, 1.0));
        metricConfigurations.add(new MetricConfiguration("LOC", "Lines Of Code", "class", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("LOCm", "Lines of Comments", "class", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("LOCM1", "Lack Of Cohesion in Methods 1", "class", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM2", "Lack Of Cohesion in Methods 2", "class", 0.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM3", "Lack Of Cohesion in Methods 3", "class", 1.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM4", "Lack Of Cohesion in Methods 4", "class", 1.0, 50.0));
        metricConfigurations.add(new MetricConfiguration("LOCM5", "Lack Of Cohesion in Methods 5", "class", 0.0, 0.8));
        metricConfigurations.add(new MetricConfiguration("NAK", "Number Of Asserts per KLOC", "class", 0.0, 1000000.0));
        metricConfigurations.add(new MetricConfiguration("NOC", "Number Of Children", "class", 0.0, 20.0));
        metricConfigurations.add(new MetricConfiguration("NOF", "Number Of Fields", "class", 0.0, 30.0));
        metricConfigurations.add(new MetricConfiguration("NOM", "Number Of Methods", "class", 0.0, 30.0));
        metricConfigurations.add(new MetricConfiguration("NOSF", "Number Of Static Fields", "class", 0.0, 30.0));
        metricConfigurations.add(new MetricConfiguration("NOSM", "Number Of Static Methods", "class", 0.0, 30.0));
        metricConfigurations.add(new MetricConfiguration("NTM", "Number of Test Methods", "class", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("WMC", "Weighted Methods Count", "class", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("TCC", "Tight Class Coupling", "class", 0.2, 1.0));
        metricConfigurations.add(new MetricConfiguration("NBD", "Average Nestted Block Depth", "class", 0.0, 7.0));
        metricConfigurations.add(new MetricConfiguration("NOP", "Total Number Of Parameters", "class", 0.0, 1000.0));
        metricConfigurations.add(new MetricConfiguration("VG", "Average McGabe's Cyclomatic Complexity", "class", 0.0, 7.0));

        metricConfigurations.add(new MetricConfiguration("LOC", "Lines Of Code", "method", 0.0, 500.0));
        metricConfigurations.add(new MetricConfiguration("LOCm", "Lines of Comments", "method", 0.0, 500.0));
        metricConfigurations.add(new MetricConfiguration("NBD", "Nested Block Depth", "method", 0.0, 10.0));
        metricConfigurations.add(new MetricConfiguration("NOP", "Number Of Parameters", "method", 0.0, 10.0));
        metricConfigurations.add(new MetricConfiguration("VG", "McGabe's Cyclomatic Complexity", "method", 0.0, 7.0));

        metricConfigurationsMap = new TreeMap<String, MetricConfiguration>();
        for (MetricConfiguration mc : metricConfigurations) {
            metricConfigurationsMap.put(mc.getSymbol() + " " + mc.getScope(), mc);
        }
    }

    public void load() {
        for (MetricConfiguration mc : metricConfigurations) {
            String min = NbPreferences.forModule(MetricConfigurations.class).get("min" + mc.getName(), "none");
            String max = NbPreferences.forModule(MetricConfigurations.class).get("max" + mc.getName(), "none");

            try {
                if (!"none".equals(min)) {
                    Double minimum = Double.valueOf(min);
                    mc.setMinimum(minimum);
                }

                if (!"none".equals(max)) {
                    Double maximum = Double.valueOf(max);
                    mc.setMaximum(maximum);

                }
            } catch (NumberFormatException e) {
                logger.severe("NumberFormatException occured when reading "
                        + "maximum and minimum values of metrics.");
            }
        }
    }

    public void store() {
        for (MetricConfiguration mc : metricConfigurations) {
            NbPreferences.forModule(MetricConfigurations.class).put("min" + mc.getName(), mc.getMinimum().toString());
            NbPreferences.forModule(MetricConfigurations.class).put("max" + mc.getName(), mc.getMaximum().toString());
        }
    }

    public List<MetricConfiguration> getMetricConfigurations() {
        return metricConfigurations;
    }

    public void setMetricConfigurations(List<MetricConfiguration> metricConfigurations) {
        this.metricConfigurations = metricConfigurations;
    }

    public Map<String, MetricConfiguration> getMetricConfigurationsMap() {
        return metricConfigurationsMap;
    }

    public static MetricConfigurations getMc() {
        return mc;
    }
    private List<MetricConfiguration> metricConfigurations;
    private Map<String, MetricConfiguration> metricConfigurationsMap;
    private static MetricConfigurations mc = new MetricConfigurations();
    private static final Logger logger = Logger.getLogger(MetricConfigurations.class.getName());
}
