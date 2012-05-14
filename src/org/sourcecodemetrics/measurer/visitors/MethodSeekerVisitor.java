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
import com.sun.source.tree.MethodTree;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.TreeScanner;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is a visitor of the class tree. it is responsible for seeking out 
 * the methods inside the class and returning all of them.
 * @author Krystian Warzocha
 */
public class MethodSeekerVisitor extends TreeScanner<Set<MethodTree>, ClassImpl> {

    @Override
    public Set<MethodTree> visitMethod(MethodTree node, ClassImpl p) {
        HashSet<MethodTree> methodTrees = null;
        if (node != null) {
            methodTrees = new HashSet<MethodTree>();
            //logger.fine("Found method " + node.getName().toString() + " in class " + p.getName());
            methodTrees.add(node);
        }
        return methodTrees;
    }

    @Override
    public Set<MethodTree> reduce(Set<MethodTree> r1, Set<MethodTree> r2) {
        if (r1 == null && r2 == null) {
            return null;
        } else if (r1 == null) {
            return r2;
        } else if (r2 == null) {
            return r1;
        } else {
            r1.addAll(r2);
            return r1;
        }
    }
    private static final Logger logger = Logger.getLogger(MethodSeekerVisitor.class.getName());
}
