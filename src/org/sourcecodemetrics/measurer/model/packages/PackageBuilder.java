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

package org.sourcecodemetrics.measurer.model.packages;

import org.sourcecodemetrics.measurer.api.BuildException;
import org.sourcecodemetrics.measurer.model.sources.SourceFileImpl;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Krystian Warzocha
 */
class PackageBuilder {

    PackageBuilder(PackageImpl p) {
        this.p = p;
    }
    
    void build() throws IOException, BuildException {
        logger.info("Started building the package " + p.name);
        if (p.fo.isFolder()) {
            // iterate over all of the source files in the package, add them to
            // the container and command them to build the tree
            for (Enumeration<? extends FileObject> e = p.fo.getData(false); e.hasMoreElements();) {
                FileObject f = e.nextElement();

                // only java files are analyzed
                if ("text/x-java".equals(f.getMIMEType())) {
                    SourceFileImpl sourceFileImpl = new SourceFileImpl(f, p.pi, p);
                    sourceFileImpl.initlize();
                    sourceFileImpl.buildAndMeasure();
                    p.classes.putAll(sourceFileImpl.getClassesMap());
                    p.sourceFiles.put(sourceFileImpl.getName(), sourceFileImpl);
                    logger.info("Found java file " + f.getNameExt() + " in package " + p.name);
                }
            }
            
        } else {
            String msg = "The package has to be folder " + p.name;
            logger.severe(msg);
            BuildException e = new BuildException(msg);
            throw e;
        }
        logger.info("Finished building the package " + p.name);
    }
    
    
    private PackageImpl p;
    private static final Logger logger = Logger.getLogger(PackageBuilder.class.getName());
}
