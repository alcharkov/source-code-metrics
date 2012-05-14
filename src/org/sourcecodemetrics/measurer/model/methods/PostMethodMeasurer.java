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

package org.sourcecodemetrics.measurer.model.methods;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.sourcecodemetrics.measurer.MeasurerImpl;
import org.sourcecodemetrics.measurer.model.classes.ClassImpl;

/**
 *
 * @author Krystian Warzocha
 */
public class PostMethodMeasurer {

    public PostMethodMeasurer(MethodImpl m) {
        this.m = m;
    }

    void postMeasure() {
        if (m.test) {

            // increase the NTM of each class under test
            Iterator<String> it = m.typesUsed.iterator();
            while (it.hasNext()) {
                String type = it.next();
                Map<String, ClassImpl> classes = MeasurerImpl.getProjectImpl().getClassesMap();

                boolean contains = false;
                for (Entry<String, ClassImpl> e : classes.entrySet()) {
                    if (type.equals(e.getKey())) {
                        ClassImpl c = e.getValue();
                        c.setNtm(c.getNTM() + 1);
                        contains = true;
                        break;
                    }
                }

                // removal of types which do not represent any class
                if (!contains) {
                    it.remove();
                }
            }

            // add assertions per KLOC to each tested class
            double avgAss = (double) m.numberOfAssertions / (double) m.typesUsed.size(); // average number of assertsions per tested class
            Map<String, ClassImpl> classes = MeasurerImpl.getProjectImpl().getClassesMap();
            for (String s : m.typesUsed) {
                ClassImpl c = classes.get(s);
                int sfLoc = c.getSf().getLOC();
                double assPerLoc = 1000.0 * avgAss / (double)sfLoc;
                c.setNak(c.getNAK() + assPerLoc);
            }
        }
    }
    private MethodImpl m;
}
