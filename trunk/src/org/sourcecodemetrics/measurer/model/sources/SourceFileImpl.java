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

package org.sourcecodemetrics.measurer.model.sources;

import org.sourcecodemetrics.measurer.api.BuildException;
import org.sourcecodemetrics.measurer.api.IClass;
import org.sourcecodemetrics.measurer.api.ISourceFile;
import org.sourcecodemetrics.measurer.model.ProjectImpl;
import org.sourcecodemetrics.measurer.model.classes.ClassImpl;
import org.sourcecodemetrics.measurer.model.packages.PackageImpl;
import com.sun.source.tree.CompilationUnitTree;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Krystian Warzocha
 */
public class SourceFileImpl implements ISourceFile {

    /**
     * Injects the source file object.
     * @param fo The source file object.
     */
    public SourceFileImpl(FileObject fo, ProjectImpl pi, PackageImpl pkg) {
        this.fo = fo;
        this.pi = pi;
        this.pkg = pkg;
    }

    public void initlize() {
        name = fo.getNameExt(); // name of the source file is the same as the name of the file
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public Collection<IClass> getClasses() {
        return (Collection<IClass>) (Collection<?>) classes.values();
    }

    public Set<Comment> getComments() {
        return comments;
    }

    @Override
    public Integer getLOC() {
        return loc;
    }

    @Override
    public Integer getLOCm() {
        return locm;
    }

    public Map<String, ClassImpl> getClassesMap() {
        return classes;
    }

    public Set<String> getImports() {
        return imports;
    }

    public PackageImpl getPkg() {
        return pkg;
    }

    public CompilationUnitTree getCut() {
        return cut;
    }

    /**
     * Builds the tree of objects which represent the language elements. 
     * Then measures the metrics.
     */
    public void buildAndMeasure() throws IOException, BuildException {
        builderAndMeasurer.buildAndMeasure();
    }

    /**
     * Performs measurements which can be done only after all of the information
     * about the source file and classes have been gathered. 
     */
    public void postMeausre() {
        measurer.measure();
    }
    TreeMap<String, ClassImpl> classes = new TreeMap<String, ClassImpl>();
    Set<String> imports = null;
    Set<Comment> comments = new HashSet<Comment>();
    CompilationUnitTree cut;
    FileObject fo;
    String name;
    String packageName;
    ProjectImpl pi;
    PackageImpl pkg;
    Integer loc;
    Integer locm;
    private SourceBuilderAndMeasurer builderAndMeasurer = new SourceBuilderAndMeasurer(this);
    private SourceMeasurer measurer = new SourceMeasurer(this);
    static final private Logger logger = Logger.getLogger(SourceFileImpl.class.getName());
}
