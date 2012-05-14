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

package org.sourcecodemetrics.measurer.model.methods;

import org.sourcecodemetrics.measurer.api.IMethod;
import org.sourcecodemetrics.measurer.model.classes.ClassImpl;
import org.sourcecodemetrics.measurer.util.MethodUtil;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.VariableTree;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Krystian Warzocha.
 */
public class MethodImpl implements IMethod {

    /**
     * Constructor injecting the method tree.
     * @param mt The method tree.
     * @param fo File where the method is defined.
     * @param ci Class where the method is defined.
     */
    public MethodImpl(MethodTree mt, FileObject fo, ClassImpl ci) {
        this.mt = mt;
        this.fo = fo;
        this.c = ci;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public FileObject getFileObject() {
        return fo;
    }

    /**
     * Returns the signature of the method.
     * @return Signature of the method.
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Initlizes the name of the method and its list of types of parameters. 
     * This allows to correctly identify method in the class.
     */
    public void initilize() {
        name = mt.getName().toString(); // name of the method
        if (name.equals("<init>")) {
            name = c.getName();
        }

        // list of types of the parameters of this method
        Iterator<? extends VariableTree> it = mt.getParameters().iterator();
        while (it.hasNext()) {
            VariableTree vt = it.next();
            parametersTypes.add(vt.getType().toString());
        }
        signature = MethodUtil.buildMethodSignature(name, parametersTypes); // building the signature of this method
    }

    /**
     * Builds the key fields of the method which are later necessary for 
     * measurement.
     */
    public void build() {
        methodBuilder.build();
    }

    /**
     * Measures all of the metrics of the method.
     */
    public void measure() throws IOException {
        methodMeasurer.measure();
    }

    public void postMeasure() {
        postMethodMeasurer.postMeasure();
    }

    @Override
    public Integer getVG() {
        return vg;
    }

    @Override
    public Integer getNBD() {
        return nbd;
    }

    @Override
    public Integer getNOP() {
        return nop;
    }

    @Override
    public Integer getLOC() {
        return loc;
    }

    @Override
    public Integer getLOCm() {
        return locm;
    }

    /**
     * Method is static or not.
     * @return Logical value indicationg whether the method is static or not.
     */
    public Boolean isStat() {
        return stat;
    }

    /**
     * Method is a test.
     * @return True is method is a test. False otherwise.
     */
    public boolean isTest() {
        return test;
    }

    /**
     * Returns the list of fields that are directly used by this method.
     * @return Fields used directly by this method.
     */
    public Set<String> getFieldsUsed() {
        return fieldsUsed;
    }

    /**
     * Returns the list of all of fields used by this method (including fields 
     * used indirectly by calling other methods).
     * @return All of the fields used by the method.
     */
    public Set<String> getAllFieldsUsed() {
        return allFieldsUsed;
    }

    /**
     * Return the set of methods which are invoked by this method.
     * @return The set of methods used.
     */
    public Set<MethodImpl> getMethodsUsed() {
        return methodsUsed;
    }

    /**
     * Determines whether this method is valid.
     * @return Valid.
     */
    public boolean isValid() {
        return valid;
    }
    MethodTree mt = null;
    FileObject fo = null;
    ClassImpl c = null;
    String signature = null;
    String name = null;
    /**
     * Contains the number of assertions inside the method.
     */
    Integer numberOfAssertions = null;
    /**
     * Contains the list of parameters that are stored by this class.
     */
    List<String> parametersTypes = new ArrayList<String>();
    /**
     * McGabe's cyclomatic complexity of this method.
     */
    Integer vg = null;
    /**
     * Nested block depth of this method.
     */
    Integer nbd = null;
    /**
     * Number Of Parameters of this method.
     */
    Integer nop = null;
    /**
     * Lines Of Code of this method.
     */
    Integer loc = null;
    /**
     * Lines Of Comment in this method.
     */
    Integer locm = null;
    /**
     * Number of Test Calls.
     */
    Integer ntc = null;
    /**
     * Determines whether this method is static.
     */
    Boolean stat = false;
    /**
     * Determines whether the method is abstract.
     */
    Boolean abstr = false;
    /**
     * Contains the set fields used by this method.
     */
    Set<String> fieldsUsed = null;
    /**
     * Contains all of the fields used by this method including fields used by 
     * methods used by this method.
     */
    Set<String> allFieldsUsed = null;
    /**
     * Contains the set of methods used by this method.
     */
    Set<MethodImpl> methodsUsed = null;
    /**
     * Contains all of the types used by this method.
     */
    Set<String> typesUsed = null;
    /**
     * Determines whether this method is a junit test method.
     */
    boolean test = false;
    /**
     * Determines whether this method is valid or not.
     */
    boolean valid = true;
    private MethodBuilder methodBuilder = new MethodBuilder(this);
    private MethodMeasurer methodMeasurer = new MethodMeasurer(this);
    private PostMethodMeasurer postMethodMeasurer = new PostMethodMeasurer(this);
    private static final Logger logger = Logger.getLogger(MethodImpl.class.getName());
}
