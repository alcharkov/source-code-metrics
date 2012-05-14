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

import org.sourcecodemetrics.measurer.model.ProjectImpl;
import org.sourcecodemetrics.measurer.model.packages.PackageImpl;
import org.sourcecodemetrics.measurer.util.MethodUtil;
import com.sun.source.tree.ImportTree;
import com.sun.source.util.TreeScanner;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Krystian Warzocha
 */
public class ImportsVisitor extends TreeScanner<Set<String>, AbstractMap.Entry<ProjectImpl, PackageImpl>> {

    @Override
    public Set<String> visitImport(ImportTree node, AbstractMap.Entry<ProjectImpl, PackageImpl> p) {
        
        ProjectImpl projectImpl = p.getKey();
        PackageImpl packageImpl = p.getValue();

        if (node != null) {
            String importIndentifier = node.getQualifiedIdentifier().toString();

            // check if this import identifier belongs to any of the packages
            String pkgStr = MethodUtil.belongsToPackages(importIndentifier, projectImpl.getPackagesMap().keySet()); 
            if (pkgStr != null) {
                PackageImpl pi = projectImpl.getPackagesMap().get(pkgStr); // retrieval of the package
                
                // adding input and output dependencies
                packageImpl.getOutputDependencies().add(pi);
                pi.getInputDependencies().add(packageImpl);
                
                HashSet<String> set = new HashSet<String>();
                set.add(importIndentifier);
                return set;
            }
        }
        return null;
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
            if (r1.size() >= r2.size()) {
                r1.addAll(r2);
                return r1;
            } else {
                r2.addAll(r1);
                return r2;
            }
        }
    }
}
