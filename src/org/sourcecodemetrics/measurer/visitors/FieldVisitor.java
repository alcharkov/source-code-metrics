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

import org.sourcecodemetrics.measurer.model.classes.ClassImpl;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.TreeScanner;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * The responsibility of this class is to build the set of fields which are 
 * defined in the specified class.
 * @author Krystian Warzocha
 */
public class FieldVisitor extends TreeScanner<Set<String>, Void> {

    @Override
    public Set<String> visitVariable(VariableTree node, Void p) {
        //return super.visitVariable(node, p);
        System.out.println("Just at the begining");
        HashSet<String> fields = null;

        if (node != null) {
            fields = new HashSet<String>();
            //logger.fine("Found field " + node.getName().toString() + " in class " p.getSimpleName());
            fields.add(node.getName().toString());
        }
        return fields;
    }

    @Override
    public Set<String> reduce(Set<String> r1, Set<String> r2) {
        if (r1 == null && r2 == null) {
            return new TreeSet<String>();
        } else if (r2 == null) {
            return r1;
        } else if (r1 == null) {
            return r2;
        } else {
            r1.addAll(r2);
            return r1;
        }
    }
    private static final Logger logger = Logger.getLogger(FieldVisitor.class.getName());
}
