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

import com.sun.source.tree.BlockTree;
import com.sun.source.util.TreeScanner;

/**
 * Counts the number of nested blocks in the method.
 * @author Krystian Warzocha
 */
public class NestedBlockCounterVisitor extends TreeScanner<Integer, Void> {

    @Override
    public Integer visitBlock(BlockTree node, Void p) {
        Integer nestedLevel = super.visitBlock(node, p);

        if (nestedLevel != null) {
            return nestedLevel + 1;
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
            return Math.max(r1, r2);
        }
    }
}
