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

package org.sourcecodemetrics.measurer.visitors;

import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreeScanner;
import javax.lang.model.element.Modifier;

/**
 *
 * @author Krystian Warzocha
 */
public class FieldCounterVisitor extends TreeScanner<Integer, Void> {

    /**
     * Constructor
     * @param stat If true then only static fields are counted. 
     */
    public FieldCounterVisitor(boolean stat) {
        this.stat = stat;
    }

    @Override
    public Integer visitVariable(VariableTree node, Void p) {
        if (node != null) {
            if (stat) {
                if(node.getModifiers().getFlags().contains(Modifier.STATIC)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public Integer reduce(Integer r1, Integer r2) {
        if (r1 == null && r2 == null) {
            return 0;
        } else if (r1 == null) {
            return r2;
        } else if (r2 == null) {
            return r1;
        } else {
            return r1 + r2;
        }
    }
    private boolean stat;
}
