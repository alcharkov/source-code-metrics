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

import org.sourcecodemetrics.measurer.api.CompilationUnitKind;
import org.sourcecodemetrics.measurer.model.sources.Comment;
import org.sourcecodemetrics.measurer.visitors.CyclomaticComplexityVisitor;
import org.sourcecodemetrics.measurer.visitors.NestedBlockCounterVisitor;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sourcecodemetrics.measurer.util.CommentUtility;

/**
 * This class is responsible for the measurement of metrics of the method.
 * @author Krystian Warzocha
 */
class MethodMeasurer {

    MethodMeasurer(MethodImpl mi) {
        this.m = mi;
    }

    void measure() throws IOException {
        logger.finest("Started measuring method " + m.signature);

        // computation of the cyclomatic complexity of this method.
        m.vg = new CyclomaticComplexityVisitor().scan(m.mt, 0) + 1;

        // computation of the nested block depth
        m.nbd = new NestedBlockCounterVisitor().scan(m.mt, null);

        // computation of the number of parameters
        m.nop = m.mt.getParameters().size();

        // building the set of methods used directly and indirectly by this method
        HashSet<MethodImpl> usedMethods = new HashSet<MethodImpl>(m.methodsUsed);
        usedMethods.add(m);
        fillUsedMethods(m, usedMethods);
        usedMethods.remove(m);

        // building the set of class fields used directly and indirectly by this method
        m.allFieldsUsed = new HashSet<String>(m.fieldsUsed);
        for (MethodImpl mi : usedMethods) {
            m.allFieldsUsed.addAll(mi.fieldsUsed);
        }

        StringBuilder sb = new StringBuilder();
        for (Iterator<String> it = m.allFieldsUsed.iterator(); it.hasNext(); sb.append("  ")) {
            sb.append(it.next());
        }

        if (m.c.getCompilationUnitKind() == CompilationUnitKind.INTERFACE) {
            m.loc = 1;
            m.locm = 0;
        } else if (m.abstr) {
            m.loc = 1;
            m.locm = 0;
        } else {
            computeLOC();
        }

        logger.finest("Method VG=" + m.vg + ", NBD=" + m.nbd + ", NOP=" + m.nop
                + ", LOC=" + m.loc + ", LOCM=" + m.locm + ", all fields used: " + sb);
        logger.finest("Finished measuring method " + m.signature);
    }

    private void computeLOC() throws IOException {
        // calculation of LOC
        m.loc = 0;
        int start = 0, end = 0; // contain the start and end elements of the 
        String text = m.fo.asText();
        String p = m.name + " *\\(.*\\) *\\{";
        Pattern pattern = Pattern.compile(p, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {

            try {

                int index = matcher.start();
                start = index;
                if (index != -1) {
                    int parethesisIndex = text.indexOf("{", index);
                    int accumulator = 1;
                    int numberOfLines = 1;
                    int i = parethesisIndex + 1;
                    while (accumulator != 0) {
                        if (text.charAt(i) == '\n') {
                            numberOfLines++;
                        } else if (text.charAt(i) == '{') {
                            if (!CommentUtility.isAComment(m.c.getSf().getComments(), i)) {
                                accumulator++;
                            }
                        } else if (text.charAt(i) == '}') {
                            if (!CommentUtility.isAComment(m.c.getSf().getComments(), i)) {
                                accumulator--;
                            }
                        }
                        i++;
                        end = i;
                        m.loc = numberOfLines;
                    }
                    end = i;
                    m.loc = numberOfLines;
                }

            } catch (StringIndexOutOfBoundsException ex) {
            }

            // calculation of LOCm
            m.locm = 0;
            for (Comment comment : m.c.getSf().getComments()) {
                if (comment.isContained(start, end)) {
                    m.locm += comment.getLines();
                }
            }
        } else {
            m.valid = false;
        }
    }

    /**
     * This method is responsible for finding recursively all of the methods 
     * used indirectly by the provided method. After this method executes the 
     * usedMethods parameter will contain all of the methods used directly and
     * indirectly by the method. 
     * @param method Method for which the set of methods used indirectly is 
     * built.
     * @param usedMethods Before method execution it should contain the list of 
     * methods used directly by the method and the method. After execution it 
     * will contain the list of methods used directly and indirectly by the 
     * method.
     */
    private void fillUsedMethods(MethodImpl method, Set<MethodImpl> usedMethods) {
        for (MethodImpl mi : method.getMethodsUsed()) {
            if (!usedMethods.contains(mi)) {
                usedMethods.add(mi);
                fillUsedMethods(mi, usedMethods);
            }
        }
    }
    private MethodImpl m;
    private static final Logger logger = Logger.getLogger(MethodMeasurer.class.getName());
}
