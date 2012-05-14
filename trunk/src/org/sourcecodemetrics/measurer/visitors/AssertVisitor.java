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

import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.util.TreeScanner;

/**
 *
 * @author Krystian Warzocha
 */
public class AssertVisitor extends TreeScanner<Integer, Object> {

    @Override
    public Integer reduce(Integer r1, Integer r2) {
        if(r1 == null && r2 == null) {
            return 0;
        } else if (r1 == null) {
            return r2;
        } else if (r2 == null) {
            return r1;
        } else {
            // both r1 and r2 are not null
            return r1 + r2;
        }
    }

    @Override
    public Integer visitMethodInvocation(MethodInvocationTree node, Object p) {
        Integer n = super.visitMethodInvocation(node, p);
        
        if(n == null) {
            n = 0;
        }
        
        String invocation = node.getMethodSelect().toString();
        if(invocation.contains("assert")) {
            n++;
        }
        
        return n;
    }
    
}
