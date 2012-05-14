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

import java.util.HashSet;
import org.sourcecodemetrics.measurer.visitors.AnnotationVisitor;
import org.sourcecodemetrics.measurer.visitors.FieldUsageVisitor;
import org.sourcecodemetrics.measurer.visitors.MethodUsageVisitor;
import java.util.Set;
import java.util.logging.Logger;
import javax.lang.model.element.Modifier;
import org.sourcecodemetrics.measurer.visitors.AssertVisitor;
import org.sourcecodemetrics.measurer.visitors.TypesVisitor;

/**
 * This class is responsible for building the fields of MethodImpl.
 * @author Krystian Warzocha
 */
public class MethodBuilder {

    public MethodBuilder(MethodImpl mi) {
        this.m = mi;
    }

    void build() {
        logger.finest("Started building method " + m.signature);

        // check if the method is abstract
        for (Modifier modifier : m.mt.getModifiers().getFlags()) {
            if (modifier.equals(Modifier.ABSTRACT)) {
                m.abstr = true;
                break;
            }
        }

        // check if this method is a test case
        Set<String> annotations = new AnnotationVisitor().scan(m.mt, null);
        m.test = annotations.contains("Test"); // JUnit4
        m.test = m.name.startsWith("test");    // JUnit3

        // building the set of all of the fields used by the method
        m.fieldsUsed = new FieldUsageVisitor().scan(m.mt, m.c.getFields());
        StringBuilder fieldsUsed = new StringBuilder();
        for (String field : m.fieldsUsed) {
            fieldsUsed.append(field).append(" ");
        }

        // building the set of methods used by the method
        m.methodsUsed = new MethodUsageVisitor().scan(m.mt, m.c);
        StringBuilder methodsUsed = new StringBuilder();
        methodsUsed.delete(0, methodsUsed.length());
        for (MethodImpl method : m.methodsUsed) {
            methodsUsed.append(method.signature).append(" ");
        }

        // collectiong all of the types used by this method
        m.typesUsed = new HashSet<String>();
        m.typesUsed = new TypesVisitor().scan(m.mt, m.c.getSf().getImports());

        // count the number of assertions inside this method
        if (m.test) {
            m.numberOfAssertions = new AssertVisitor().scan(m.mt, null);
        }

        // determining if this method is static or not
        m.stat = m.mt.getModifiers().getFlags().contains(Modifier.STATIC);

        logger.finest("Method is a test: " + m.test + ", static: " + m.stat
                + ", fields used: " + fieldsUsed + ", methods used: " + methodsUsed);
        logger.finest("Finished building method " + m.signature);
    }
    private MethodImpl m;
    private static final Logger logger = Logger.getLogger(MethodBuilder.class.getName());
}
