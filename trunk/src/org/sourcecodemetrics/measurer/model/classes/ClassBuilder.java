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

package org.sourcecodemetrics.measurer.model.classes;

import org.sourcecodemetrics.measurer.api.CompilationUnitKind;
import org.sourcecodemetrics.measurer.model.methods.MethodImpl;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.tree.VariableTree;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.lang.model.element.Modifier;

/**
 * This class is responsible for initilization of fields of the ClassImpl class.
 * @author Krystian Warzocha
 */
public class ClassBuilder {

    public ClassBuilder(ClassImpl c) {
        this.c = c;
    }

    public void build() {
        logger.finer("Started building class " + c.fullName);

        // unit is abstract if it is an abstract class or an interface
        c.abstr = c.ct.getModifiers().getFlags().contains(Modifier.ABSTRACT) || (c.ct.getKind() == Kind.INTERFACE);

        // collecting the set of fields which belong to this class
        Set<MethodTree> mts = buildMembers();

        // add all of the methods found to the set of methods of this class,
        // initlize them and build them.
        for (MethodTree mt : mts) {
            MethodImpl methodImpl = new MethodImpl(mt, c.fo, c);
            methodImpl.initilize();
            c.methods.put(methodImpl.getSignature(), methodImpl);
        }

        // build all of the methods
        for (MethodImpl mi : c.methods.values()) {
            mi.build();
        }

        // set the kind of the compilation unit
        switch (c.ct.getKind()) {
            case CLASS:
                c.cuk = CompilationUnitKind.CLASS;
                break;
            case ENUM:
                c.cuk = CompilationUnitKind.ENUMERATION;
                break;
            case INTERFACE:
                c.cuk = CompilationUnitKind.INTERFACE;
                break;
            default:
                c.cuk = null;
        }

        // getting the identifier of the extended class
        Tree extendsTree = c.ct.getExtendsClause();
        if (extendsTree != null) {
            c.extendedClassIdentifier = extendsTree.toString();
        }

        logger.finer("Class " + c.name + " is abstract: " + c.abstr + " and is " + c.ct.getKind().name());
        logger.finer("Finished building class " + c.fullName);
    }

    /**
     * Builds the set of fields and methods belonging to this class.
     * @return Set of methods belonging to this class.
     */
    private Set<MethodTree> buildMembers() {
        c.fields = new HashMap<String, String>();
        Set<MethodTree> mts = new HashSet<MethodTree>();

        // check all of the members of this class
        for (Tree t : c.ct.getMembers()) {
            if (t.getKind() == Kind.VARIABLE) {
                VariableTree vt = (VariableTree) t;
                
                String type = vt.getType().toString();
                
                // building the full type name
                String fullTypeName = type;
                for(String i : c.sf.getImports()) {
                    if(i.endsWith(type)) {
                        fullTypeName = i;
                        break;
                    }
                }
                
                c.fields.put(vt.getName().toString(), fullTypeName);
            } else if (t.getKind() == Kind.METHOD) {
                MethodTree mt = (MethodTree) t;
                mts.add(mt);
            }
        }

        return mts;
    }
    private ClassImpl c;
    private static final Logger logger = Logger.getLogger(ClassBuilder.class.getName());
}
