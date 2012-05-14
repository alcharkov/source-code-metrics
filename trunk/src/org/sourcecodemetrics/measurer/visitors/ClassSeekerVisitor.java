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

import com.sun.source.tree.ClassTree;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.TreeScanner;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for seeking the whole source file for the classes
 * definitions. After it finds all of the classes then the list of classes can
 * be retrieved.
 * @author Krystian Warzocha
 */
public class ClassSeekerVisitor extends TreePathScanner<Set<ClassTree>, Void> {

    @Override
    public Set<ClassTree> visitClass(ClassTree node, Void p) {
        Set<ClassTree> subClasses = super.visitClass(node, p); // visiting descendant nodes
        
        HashSet<ClassTree> classTrees = new HashSet<ClassTree>();
        classTrees.add(node);

        // if the node being visited has no children then the result is null
        if (subClasses != null) {
            classTrees.addAll(subClasses);
        }

        return classTrees;
    }

    @Override
    public Set<ClassTree> reduce(Set<ClassTree> r1, Set<ClassTree> r2) {
        if (r1 == null && r2 == null) {
            return null;
        } else if (r1 == null) {
            return r2;
        } else if (r2 == null) {
            return r1;
        } else {
            // both r1 and r2 are not null
            r1.addAll(r2);
            return r1;
        }
    }
}
