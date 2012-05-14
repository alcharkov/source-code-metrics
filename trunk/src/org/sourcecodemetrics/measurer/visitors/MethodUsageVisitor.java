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
import org.sourcecodemetrics.measurer.model.methods.MethodImpl;
import org.sourcecodemetrics.measurer.util.MethodUtil;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.TreeScanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * The role of this class is to buld a set of class methods used by the 
 * specified method.
 * @author Krystian Warzocha
 */
public class MethodUsageVisitor extends TreeScanner<Set<MethodImpl>, ClassImpl> {

    @Override
    public Set<MethodImpl> visitMethodInvocation(MethodInvocationTree node, ClassImpl p) {
        Set<MethodImpl> methods = super.visitMethodInvocation(node, p);
        if(methods == null) {
            methods = new HashSet<MethodImpl>();
        }
        
        // build the MethodImpl object with the attributes of the method invocation
        String methodName = node.getMethodSelect().toString();
        List<String> arguments = new ArrayList<String>();
        for(Tree t : node.getTypeArguments()) {
            arguments.add(t.toString());
        }
        String signature = MethodUtil.buildMethodSignature(methodName, arguments);
        MethodImpl mi = p.getMethod(signature);
        
        // add the method only if it has been found in the class
        if(mi != null) {
            methods.add(mi);
        }
        return methods;
    }

    @Override
    public Set<MethodImpl> reduce(Set<MethodImpl> r1, Set<MethodImpl> r2) {
        if (r1 == null && r2 == null) {
            return new HashSet<MethodImpl>();
        } else if (r2 == null) {
            return r1;
        } else if (r1 == null) {
            return r2;
        } else {
            if (r1.size() > r2.size()) {
                r1.addAll(r2);
                return r1;
            } else {
                r2.addAll(r1);
                return r2;
            }
        }
    }
    private static final Logger logger = Logger.getLogger(MethodUsageVisitor.class.getName());
}
