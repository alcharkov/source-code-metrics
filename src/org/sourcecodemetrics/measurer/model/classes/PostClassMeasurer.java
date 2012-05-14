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

package org.sourcecodemetrics.measurer.model.classes;

import org.sourcecodemetrics.measurer.MeasurerImpl;
import java.util.Collection;
import java.util.Map;
import org.sourcecodemetrics.measurer.model.methods.MethodImpl;

/**
 *
 * @author Krystian Warzocha
 */
public class PostClassMeasurer {

    PostClassMeasurer(ClassImpl c) {
        this.c = c;
    }

    void postMeasure() {
        Map<String, ClassImpl> classes = MeasurerImpl.getProjectImpl().getClassesMap();

        if (c.ntm > 0) {
            for (String s : c.sf.getImports()) {
                if (classes.keySet().contains(s)) {
                    ClassImpl coveredClass = classes.get(s);
                    coveredClass.setCovered(true);
                }
            }
        }

        if (c.extendedClassIdentifier != null) {
            boolean found = false;
            // first check classes in the same package
            Collection<ClassImpl> pc = c.sf.getPkg().getClasses();
            for (ClassImpl ci : pc) {
                if (c.extendedClassIdentifier.equals(ci.name)) {
                    ci.setNOC(ci.getNOC() + 1);
                    found = true;
                    break;
                }
            }

            // calculation of the number of children
            if (!found) {
                for (String i : c.sf.getImports()) {
                    if (i.contains(c.extendedClassIdentifier)) {
                        ClassImpl extendedClass = classes.get(i);
                        if (extendedClass != null) {
                            extendedClass.setNOC(extendedClass.getNOC() + 1);
                        }
                    }
                }
            }
        }
        
        // perform the post measurement for every method
        for(MethodImpl mi : c.getMethodImpls()) {
            mi.postMeasure();
        }
    }
    private ClassImpl c;
}
