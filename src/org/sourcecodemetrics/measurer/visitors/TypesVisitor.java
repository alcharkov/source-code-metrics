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

import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreeScanner;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Krystian Warzocha
 */
public class TypesVisitor extends TreeScanner<Set<String>, Set<String>> {

    @Override
    public Set<String> visitVariable(VariableTree variable, Set<String> imports) {
        Set<String> types = super.visitVariable(variable, imports);
        if (types == null) {
            types = new HashSet<String>();
        }

        if (variable != null) {
            String type = variable.getType().toString();

            for (String i : imports) {
                if (i.endsWith(type)) {
                    types.add(i);
                    return types;
                }
            }

            types.add(type);
        }

        return types;
    }

    @Override
    public Set<String> visitMethodInvocation(MethodInvocationTree invocation, Set<String> imports) {
        Set<String> types = super.visitMethodInvocation(invocation, imports);

        if (types == null) {
            types = new HashSet<String>();
        }

        if (invocation != null) {
            ExpressionTree et = invocation.getMethodSelect();
            if (Kind.MEMBER_SELECT.equals(et.getKind())) {
                MemberSelectTree ms = (MemberSelectTree) et;
                String memberSelection = ms.getExpression().toString();

                for (String i : imports) {
                    if (i.endsWith(memberSelection)) {
                        types.add(i);
                        return types;
                    }
                }

                types.add(memberSelection);
            }
        }

        return types;
    }

    @Override
    public Set<String> reduce(Set<String> r1, Set<String> r2) {
        if (r1 == null && r2 == null) {
            return new HashSet<String>();
        } else if (r1 == null) {
            return r2;
        } else if (r2 == null) {
            return r1;
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
}
