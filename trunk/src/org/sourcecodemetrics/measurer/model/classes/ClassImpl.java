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

import org.sourcecodemetrics.measurer.model.methods.MethodImpl;
import org.sourcecodemetrics.measurer.api.CompilationUnitKind;
import org.sourcecodemetrics.measurer.api.IClass;
import org.sourcecodemetrics.measurer.api.IMethod;
import org.sourcecodemetrics.measurer.model.sources.SourceFileImpl;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.util.TreePath;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Krystian Warzocha
 */
public class ClassImpl implements IClass {

    /**
     * Construtor used for injecting the class tree.
     * @param ct The tree of the class.
     * @param fo 
     */
    public ClassImpl(ClassTree ct, FileObject fo, SourceFileImpl sf) {
        this.ct = ct;
        this.fo = fo;
        this.sf = sf;
    }

    /**
     * Initlizes the name field of the class.
     */
    public void initlize() {
        name = ct.getSimpleName().toString();

        // build the path to the inner class by inspecting the path to the class
        StringBuilder sb = new StringBuilder();
        TreePath treePath = TreePath.getPath(sf.getCut(), ct);
        Iterator<Tree> it = treePath.iterator();
        while (it.hasNext()) {
            Tree tree = it.next();
            if (tree.getKind() == Kind.CLASS && tree != ct) {
                ClassTree classTree = (ClassTree) tree;
                sb.append(classTree.getSimpleName()).append(".");
            }
        }

        if (sf.getPackageName() != null) {
            fullName = sf.getPackageName() + "." + sb.toString() + name;
        } else {
            fullName = name;
        }
    }
    
    public boolean isMainClass() {
        if(sf.getName().contains(name)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public FileObject getFileObject() {
        return fo;
    }

    @Override
    public CompilationUnitKind getCompilationUnitKind() {
        return cuk;
    }

    @Override
    public Collection<IMethod> getMethods() {
        return (Collection<IMethod>) (Collection<?>) methods.values();
    }

    public Collection<MethodImpl> getMethodImpls() {
        return methods.values();
    }

    public Set<String> getFields() {
        return fields.keySet();
    }

    public boolean isAbstr() {
        return abstr;
    }

    /**
     * This method allows to query and retrieve a method of this class by 
     * @param signature The signature of the method.
     * @return The method which has the specified signature, null if method
     * with this signature does not exist.
     */
    public MethodImpl getMethod(String signature) {
        return methods.get(signature);
    }

    public SourceFileImpl getSf() {
        return sf;
    }

    @Override
    public Integer getNOM() {
        return methods.size();
    }

    @Override
    public Integer getNOSM() {
        return nosm;
    }

    @Override
    public Integer getNOF() {
        return nof;
    }

    @Override
    public Integer getNOSF() {
        return nosf;
    }

    @Override
    public Integer getLCOM1() {
        return lcom1;
    }

    @Override
    public Integer getLCOM2() {
        return lcom2;
    }

    @Override
    public Integer getLCOM3() {
        return lcom3;
    }

    @Override
    public Integer getLCOM4() {
        return lcom4;
    }

    @Override
    public Double getLCOM5() {
        return lcom5;
    }

    @Override
    public Double getTCC() {
        return tcc;
    }

    @Override
    public Double getLCC() {
        return lcc;
    }

    @Override
    public Integer getWMC() {
        return wmc;
    }

    @Override
    public Integer getLOC() {
        return loc;
    }

    @Override
    public Integer getLOCm() {
        return locm;
    }

    @Override
    public Integer getNTM() {
        return ntm;
    }

    public void setNtm(Integer ntm) {
        this.ntm = ntm;
    }

    @Override
    public Double getNAK() {
        return nak;
    }

    public void setNak(Double nak) {
        this.nak = nak;
    }

    @Override
    public Integer getMethodNBDMax() {
        return nbdMax;
    }

    @Override
    public Integer getMethodNBDMin() {
        return nbdMin;
    }

    @Override
    public Integer getMethodNBDAvg() {
        return nbdAvg;
    }

    @Override
    public Integer getMethodNOPMax() {
        return nopMax;
    }

    @Override
    public Integer getMethodNOPMin() {
        return nopMin;
    }

    @Override
    public Integer getMethodNOPSum() {
        return nopSum;
    }

    @Override
    public Integer getMethodVGMax() {
        return vgMax;
    }

    @Override
    public Integer getMethodVGMin() {
        return vgMin;
    }

    @Override
    public Integer getMethodVGAvg() {
        return vgAvg;
    }

    @Override
    public Integer getMethodLOCMax() {
        return locMax;
    }

    @Override
    public Integer getMethodLOCMin() {
        return locMin;
    }

    @Override
    public Integer getMethodLOCmMax() {
        return locmMax;
    }

    @Override
    public Integer getMethodLOCmMin() {
        return locmMin;
    }

    @Override
    public Integer getNOC() {
        return noc;
    }

    public void setNOC(Integer noc) {
        this.noc = noc;
    }

    @Override
    public Boolean getC() {
        return covered;
    }

    public void setCovered(Boolean covered) {
        this.covered = covered;
    }

    public String getExtendedClassIdentifier() {
        return extendedClassIdentifier;
    }

    public void build() {
        builder.build();
    }

    public void measure() throws IOException {
        measurer.measure();
    }

    public void postMeasure() {
        postMeasurer.postMeasure();
    }
    String name = null;
    String fullName = null;
    ClassTree ct;
    CompilationUnitKind cuk = null;
    TreeMap<String, MethodImpl> methods = new TreeMap<String, MethodImpl>();
    Map<String, String> fields;
    Integer nof = null;
    Integer nosm = null;
    Integer nosf = null;
    Integer lcom1 = null, lcom2 = null, lcom3 = null, lcom4 = null;
    Double lcom5 = null;
    Double tcc = null, lcc = null;
    Integer wmc = null;
    Integer loc = null;
    Integer locm = null;
    Integer ntm = 0;
    Double nak = 0.0;
    Integer noc = 0;
    Integer vgMax = null, vgMin = null, vgAvg = null;
    Integer nbdMax = null, nbdMin = null, nbdAvg = null;
    Integer nopMax = null, nopMin = null, nopSum = null;
    Integer locMax = null, locMin = null;
    Integer locmMax = null, locmMin = null;
    FileObject fo;
    SourceFileImpl sf;
    boolean abstr = false;
    Boolean covered = false;
    String extendedClassIdentifier = null;
    private ClassBuilder builder = new ClassBuilder(this);
    private ClassMeasurer measurer = new ClassMeasurer(this);
    private PostClassMeasurer postMeasurer = new PostClassMeasurer(this);
}
