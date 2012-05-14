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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FindAndUnion<P> {
    Collection<Set<P>> set = new HashSet<Set<P>>();

    public FindAndUnion(Collection<P> c) {        
        for(P p: c){
            Set<P> innerSet = new HashSet<P>();
            innerSet.add(p);
            set.add(innerSet);
        }
    }
    
    public Set<P> find(P p){
        for(Set<P> s: set){
            if(s.contains(p)) return s;
        }
        return null;            
    }
    
    public void union(P p1, P p2){
        Set<P> s1 = find(p1);
        Set<P> s2 = find(p2);
        if (s1 == null || s2 == null) throw new IllegalArgumentException();
        if (s1 == s2) return;
        
        if (s1.size() > s2.size()){
            s1.addAll(s2);
            set.remove(s2);
        } else {
            s2.addAll(s1);
            set.remove(s1);
        }          
    }

    public int size() {
        return set.size();
    }

    public Collection<Set<P>> getSet() {
        return set;
    }       
}
