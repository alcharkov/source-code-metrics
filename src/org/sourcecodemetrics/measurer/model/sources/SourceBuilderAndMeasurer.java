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
import org.sourcecodemetrics.measurer.model.ProjectImpl;
import org.sourcecodemetrics.measurer.model.classes.ClassImpl;
import org.sourcecodemetrics.measurer.model.packages.PackageImpl;
import org.sourcecodemetrics.measurer.visitors.ClassSeekerVisitor;
import org.sourcecodemetrics.measurer.visitors.ImportsVisitor;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.ExpressionTree;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.netbeans.api.java.source.CompilationController;
import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.java.source.Task;

/**
 *
 * @author Krystian Warzocha
 */
public class SourceBuilderAndMeasurer {

    SourceBuilderAndMeasurer(SourceFileImpl s) {
        this.s = s;
    }

    void buildAndMeasure() throws IOException, BuildException {

        JavaSource javaSource = JavaSource.forFileObject(s.fo);
        if (javaSource != null) {

            // calculation of lines of code
            s.loc = s.fo.asLines().size();

            // counting the comment lines
            s.locm = 0;

            // find and store all of multiline comments
            Pattern multiLinePattern = Pattern.compile("/\\*(.)*?\\*/", Pattern.DOTALL);
            Matcher matcher = multiLinePattern.matcher(s.fo.asText());
            while (matcher.find()) {
                String text = matcher.group();
                int start = matcher.start();
                int end = matcher.end();
                int lines = text.split("\n").length; // count number of lines
                Comment comment = new Comment(start, end, lines);
                s.comments.add(comment);
                s.locm += lines; // count number of comment lines
            }

            // find and store all of single line comments
            //Pattern singleLinePattern = Pattern.compile("^\\s*//.*$");
            Pattern singleLinePattern = Pattern.compile("[\\r\\n]\\s*//.*[\\r\\n]");
            matcher = singleLinePattern.matcher(s.fo.asText());
            while (matcher.find()) {
                String text = matcher.group();

                // make sure that this comment is not contained inside any other multiline comment
                if (!commentsContain(matcher.start(), matcher.end())) {
                    Comment comment = new Comment(matcher.start(), matcher.end(), 1);
                    s.comments.add(comment);
                    s.locm++;
                }
            }

            // command the java file to perform the measurements of the metrics
            javaSource.runUserActionTask(new MeasurementTask(), true);

        } else {
            String msg = "Java source file with the name " + s.name + " is null";
            BuildException be = new BuildException(msg);
            logger.log(Level.SEVERE, msg, be);
            throw be;
        }
    }

    private boolean commentsContain(int start, int end) {
        for (Comment c : s.comments) {
            if (c.contains(start, end)) {
                return true;
            }
        }
        return false;
    }

    private class MeasurementTask implements Task<CompilationController> {

        @Override
        public void run(CompilationController cc) throws Exception {
            cc.toPhase(JavaSource.Phase.ELEMENTS_RESOLVED);
            s.cut = cc.getCompilationUnit();

            ExpressionTree pn = s.cut.getPackageName();
            s.packageName = pn == null ? "" : pn.toString();

            // find all of the classes defined in this source file
            Set<ClassTree> classes = new ClassSeekerVisitor().scan(s.cut, null);

            // find the imports in the source file which point to the other packages0
            s.imports = new ImportsVisitor().scan(s.cut, new AbstractMap.SimpleEntry<ProjectImpl, PackageImpl>(s.pi, s.pkg));

            // correct input and output dependencies by removing coupling to itself
            s.pkg.getInputDependencies().remove(s.pkg);
            s.pkg.getOutputDependencies().remove(s.pkg);

            // Add all of the classes to the list of classes, initlize and build each class
            if (classes != null) {
                for (ClassTree ct : classes) {
                    ClassImpl classImpl = new ClassImpl(ct, s.fo, s);
                    classImpl.initlize();
                    // not taking into account inner classes
                    if (classImpl.getName() != null && !"".equals(classImpl.getName())) {
                        s.classes.put(classImpl.getFullName(), classImpl);
                        classImpl.build();
                    }
                }

                // Command all of the classes to perfom measurements
                for (ClassImpl classImpl : s.classes.values()) {
                    classImpl.measure();
                }
            }
        }
    }
    private SourceFileImpl s;
    private static final Logger logger = Logger.getLogger(SourceBuilderAndMeasurer.class.getName());
}
