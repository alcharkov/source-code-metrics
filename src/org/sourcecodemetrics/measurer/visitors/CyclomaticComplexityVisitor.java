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

import com.sun.source.tree.CaseTree;
import com.sun.source.tree.ConditionalExpressionTree;
import com.sun.source.tree.DoWhileLoopTree;
import com.sun.source.tree.ForLoopTree;
import com.sun.source.tree.IfTree;
import com.sun.source.tree.WhileLoopTree;
import com.sun.source.util.TreeScanner;

/**
 * This class is responsible for computing the cyclomatic complexity of a 
 * given method.
 * @author Krystian Warzocha
 */
public class CyclomaticComplexityVisitor extends TreeScanner<Integer, Integer> {

    @Override
    public Integer visitForLoop(ForLoopTree node, Integer p) {
        return super.visitForLoop(node, p) + 1;
    }

    @Override
    public Integer visitWhileLoop(WhileLoopTree node, Integer p) {
        return super.visitWhileLoop(node, p) + 1;
    }

    @Override
    public Integer visitDoWhileLoop(DoWhileLoopTree node, Integer p) {
        return super.visitDoWhileLoop(node, p) + 1;
    }

    @Override
    public Integer visitIf(IfTree node, Integer p) {
        return super.visitIf(node, p) + 1;
    }

    @Override
    public Integer visitCase(CaseTree node, Integer p) {
        return super.visitCase(node, p) + 1;
    }

    @Override
    public Integer visitConditionalExpression(ConditionalExpressionTree node, Integer p) {
        return super.visitConditionalExpression(node, p) + 1;
    }

    @Override
    public Integer reduce(Integer r1, Integer r2) {
        return (r1 == null ? 0 : r1) + (r2 == null ? 0 : r2);
    }
}