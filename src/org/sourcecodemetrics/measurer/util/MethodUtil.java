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

package org.sourcecodemetrics.measurer.util;

import java.util.List;
import java.util.Set;

/**
 * Contains utility methods associated with the methods.
 * @author Krystian Warzocha
 */
public class MethodUtil {
    
    /**
     * Builds the signature of the method. Signature without the accessor 
     * parameters and the return types.
     * @param name Name of the method.
     * @param types List of types of the method.
     * @return The signature of the method.
     */
    public static String buildMethodSignature(String name, List<String> types) {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("(");
        for(int i = 0; i < types.size(); i++) {
            sb.append(types.get(i));
            if(i < types.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * Determines if the specified import identifer represeting class or package 
     * belongs to one of the packages in the provided set of packages.
     * @param identifier Import identifier.
     * @param packages Set of packages which might contain the provided identifer.
     * @return Identifier of the package to which the identifier belongs, null 
     * oterwise.
     */
    public static String belongsToPackages(String identifier, Set<String> packages) {
        
        int lastDotIndex = identifier.lastIndexOf(".");
        String partialIdentifier = identifier;
        
        // as long as there are parts of the identifier to cut
        while(lastDotIndex != -1) {
            
            // cut the last part of the java qualified name
            partialIdentifier = partialIdentifier.substring(0, lastDotIndex);
            
            // check if this identifier is the identifier of one of the packages
            if(packages.contains(partialIdentifier)) {
                return partialIdentifier;
            }
            
            // find next place to cut
            lastDotIndex = partialIdentifier.lastIndexOf(".");
        }
        
        return null;
    }
}
