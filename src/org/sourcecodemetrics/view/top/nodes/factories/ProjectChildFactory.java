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

import org.sourcecodemetrics.measurer.api.IPackage;
import org.sourcecodemetrics.measurer.api.IProject;
import java.util.List;
import org.sourcecodemetrics.view.top.nodes.PackageNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 * Factory for nodes of the whole project itself.
 * @author Krystian Warzocha
 */
public class ProjectChildFactory extends ChildFactory<IPackage> {

    public ProjectChildFactory(IProject project) {
        this.project = project;
    }

    @Override
    protected boolean createKeys(List<IPackage> list) {
        if (project != null) {
            for (IPackage ip : project.getPackages()) {
                list.add(ip);
            }
        }

        return true;
    }

    @Override
    protected Node createNodeForKey(IPackage key) {
        if (!key.isTests() && !key.getSourceFiles().isEmpty()) {
            return new PackageNode(key);
        } else {
            return null;
        }
    }
    private IProject project;
}
