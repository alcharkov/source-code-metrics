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

package org.sourcecodemetrics.view.top;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Krystian Warzocha
 */
public class MetricsNames {

    public static final Map<String, String> packageMetrics = new TreeMap<String, String>();
    public static final Map<String, String> class1Metrics = new TreeMap<String, String>();
    public static final Map<String, String> class2Metrics = new TreeMap<String, String>();
    public static final Map<String, String> methodMetrics = new TreeMap<String, String>();
    public static final Map<String, String> commonMetrics = new TreeMap<String, String>();
    
    static {
        packageMetrics.put("A", "Abstractness");
        packageMetrics.put("AC", "Afferent Coupling");
        packageMetrics.put("D", "Distance");
        packageMetrics.put("EC", "Efferent Coupling");
        packageMetrics.put("I", "Instability");
        packageMetrics.put("NCP", "Number of Classes in Package");
        packageMetrics.put("NIP", "Number of Interfaces in Package");
        packageMetrics.put("C", "Coverage");

        class1Metrics.put("LCC", "Loose Class Coupling");
        class1Metrics.put("LCOM1", "Lack of COhesion in Methods 1");
        class1Metrics.put("LCOM2", "Lack of COhesion in Methods 2");
        class1Metrics.put("LCOM3", "Lack of COhesion in Methods 3");
        class1Metrics.put("LCOM4", "Lack of COhesion in Methods 4");
        class1Metrics.put("LCOM5", "Lack of COhesion in Methods 5");
        class1Metrics.put("NAK", "Number of Assertions per KLOC");
        class2Metrics.put("NOC", "Number Of Children");
        class2Metrics.put("NOF", "Number Of Fields");
        class2Metrics.put("NOM", "Number Of Methods");
        class2Metrics.put("NOSF", "Number Of Static Fields");
        class2Metrics.put("NOSM", "Number Of Static Methods");
        class2Metrics.put("NTM", "Number of Test Methods");
        class2Metrics.put("TCC", "Tight Class Coupling");
        class2Metrics.put("WMC", "Weighted Method Count");

        methodMetrics.put("NBD", "Nested Block Depth");
        methodMetrics.put("NOP", "Number Of Parameters");
        methodMetrics.put("VG", "McCabe's Cyclomatic Complexity");
        
        commonMetrics.put("LOC", "Lines Of Code");
        commonMetrics.put("LOCm", "Lines Of Comments");
    }
}
