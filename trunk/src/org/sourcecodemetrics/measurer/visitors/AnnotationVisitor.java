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

import com.sun.source.tree.AnnotationTree;
import com.sun.source.util.TreeScanner;
import java.util.HashSet;
import java.util.Set;
import javax.print.attribute.HashAttributeSet;

/**
 *
 * @author Krystian
 */
public class AnnotationVisitor extends TreeScanner<Set<String>, Object> {

    @Override
    public Set<String> reduce(Set<String> r1, Set<String> r2) {
        if(r1 == null && r2 == null) {
            return new HashSet<String>();
        } else if(r1 == null) {
            return r2;
        } else if(r2 == null) {
            return r1;
        } else {
            r1.addAll(r2);
            return r1;
        }
    }

    @Override
    public Set<String> visitAnnotation(AnnotationTree node, Object p) {
        Set<String> annotations = super.visitAnnotation(node, p);
        
        if(annotations == null) {
            annotations = new HashSet<String>();
        }
        
        if(node != null) {
            annotations.add(node.getAnnotationType().toString());
        }
        
        return annotations;
    }
    
}
