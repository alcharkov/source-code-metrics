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

import com.sun.source.tree.ImportTree;
import com.sun.source.util.TreeScanner;

/**
 *
 * @author Krystian Warzocha
 */
public class ImportIdentifierSeeker extends TreeScanner<String, String> {

    @Override
    public String reduce(String r1, String r2) {
        if(r1 == null) {
            return r2;
        } else if(r2 == null) {
            return r1;
        } else {
            return r1;
        }
    }

    @Override
    public String visitImport(ImportTree node, String p) {
        String id = super.visitImport(node, p);
        
        if(id != null) {
            return id;
        }
        
        String i = node.getQualifiedIdentifier().toString();
        if(i.contains(p)) {
            return i;
        }
        
        return null;
    }
    
}
