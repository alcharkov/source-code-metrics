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

import com.sun.source.tree.IdentifierTree;
import com.sun.source.util.TreeScanner;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * The role of this class is to buld a set of class fields used by the specified 
 * method.
 * @author Krystian Warzocha
 */
public class FieldUsageVisitor extends TreeScanner<Set<String>, Set<String>> {

    @Override
    public Set<String> visitIdentifier(IdentifierTree node, Set<String> p) {
        Set<String> identifiers = super.visitIdentifier(node, p);
        if (identifiers == null) {
            identifiers = new HashSet<String>();
        }
        if (node != null) {
            String fieldName = node.getName().toString();

            // make sure that this identifier represents a class field
            if (p.contains(fieldName)) {
                identifiers.add(fieldName);
            }
        }

        return identifiers;
    }
    @Override
    public Set<String> reduce(Set<String> r1, Set<String> r2) {
        if (r1 == null && r2 == null) {
            return new HashSet<String>();
        } else if (r2 == null) {
            return r1;
        } else if (r1 == null) {
            return r2;
        } else {
            r1.addAll(r2);
            return r1;
        }
    }
    private static final Logger logger = Logger.getLogger(FieldUsageVisitor.class.getName());
}
