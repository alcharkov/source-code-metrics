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

package org.sourcecodemetrics.view.top.nodes.factories;

import org.sourcecodemetrics.measurer.api.IClass;
import org.sourcecodemetrics.measurer.api.ISourceFile;
import java.util.List;
import org.sourcecodemetrics.view.top.nodes.ClassNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 * Factory of classes nodes for the source file.
 * @author Krystian Warzocha
 */
public class SourceFileChildFactory extends ChildFactory<IClass> {

    public SourceFileChildFactory(ISourceFile srcFile) {
        this.srcFile = srcFile;
    }

    @Override
    protected boolean createKeys(List<IClass> list) {
        if (srcFile != null) {
            for (IClass ic : srcFile.getClasses()) {
                list.add(ic);
            }
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(IClass key) {
        return new ClassNode(key);
    }
    private ISourceFile srcFile;
}
