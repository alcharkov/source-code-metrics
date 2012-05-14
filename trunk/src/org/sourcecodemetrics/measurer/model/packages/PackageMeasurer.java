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
import org.sourcecodemetrics.measurer.api.IClass;
import org.sourcecodemetrics.measurer.api.ISourceFile;
import org.sourcecodemetrics.measurer.model.classes.ClassImpl;
import java.io.IOException;
import java.util.logging.Logger;
import org.sourcecodemetrics.measurer.model.sources.SourceFileImpl;

/**
 *
 * @author Krystian
 */
class PackageMeasurer {

    PackageMeasurer(PackageImpl p) {
        this.p = p;
    }

    void measure() throws IOException, BuildException {
        logger.fine("Started measuring package " + p.name);

        // computation of metrics NCP and NIP
        p.ncp = 0;
        p.nip = 0;
        for (ISourceFile isf : p.sourceFiles.values()) {
            for (IClass ic : isf.getClasses()) {
                switch (ic.getCompilationUnitKind()) {
                    case CLASS:
                        p.ncp++;
                        break;
                    case INTERFACE:
                        p.nip++;
                        break;
                }
            }
        }

        // calculation of Abstractness
        if (p.classes.size() > 0) {
            int numOfAbstrClasses = 0;
            for (ClassImpl c : p.classes.values()) {
                if (c.isAbstr()) {
                    numOfAbstrClasses++;
                }
            }
            p.a = ((double) numOfAbstrClasses) / ((double) p.classes.size());
        } else {
            p.a = 0.0;
        }

        // calculation of coupling
        p.ac = p.inputDependencies.size();
        p.ec = p.outputDependencies.size();

        // calculation of instability
        if (p.ac == 0 && p.ec == 0) {
            // there are absolutely no dependencies 
            p.i = 0.0;
        } else {
            p.i = ((double) p.ec) / ((double) (p.ac + p.ec));
        }

        // calculation of distance
        p.d = Math.abs((p.a + p.i - 1.0) / Math.sqrt(2.0));

        logger.fine("Finished measuring package " + p.name);
    }
    private PackageImpl p;
    private static final Logger logger = Logger.getLogger(PackageMeasurer.class.getName());
}
