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

import org.sourcecodemetrics.measurer.model.sources.SourceFileImpl;
import org.sourcecodemetrics.measurer.MeasurerImpl;
import org.sourcecodemetrics.measurer.api.BuildException;
import org.sourcecodemetrics.measurer.api.IPackage;
import org.sourcecodemetrics.measurer.api.ISourceFile;
import org.sourcecodemetrics.measurer.model.ProjectImpl;
import org.sourcecodemetrics.measurer.model.classes.ClassImpl;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Krystian Warzocha
 */
public class PackageImpl implements IPackage {

    /**
     * Constructor injecting the file object directory.
     * @param fo The directory representing the package.
     */
    public PackageImpl(FileObject fo, ProjectImpl pi, boolean defaultPackage, boolean tests) {
        this.fo = fo;
        this.pi = pi;
        this.defaultPackage = defaultPackage;
        this.tests = tests;
    }

    /**
     * Initializes the name of the package
     */
    public void initlize() {
        if (!defaultPackage) {
            FileObject src = tests ? MeasurerImpl.getProjectImpl().getTest() : MeasurerImpl.getProjectImpl().getSrc();
            FileObject f = fo;
            StringBuilder sb = new StringBuilder();

            while (f != src) {
                sb.insert(0, f.getName());
                sb.insert(0, ".");
                f = f.getParent();
            }

            name = sb.substring(1);
        } else {
            if (!tests) {
                name = "(default package)";
            } else {
                name = "(default tests package)";
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isTests() {
        return tests || testPackage;
    }

    @Override
    public Collection<ISourceFile> getSourceFiles() {
        return (Collection<ISourceFile>) (Collection<?>) sourceFiles.values();
    }

    public Collection<ClassImpl> getClasses() {
        return classes.values();
    }

    /**
     * Determines whether the package should be displayed.
     * @return Display the package or not.
     */
    public boolean isValid() {
        return !sourceFiles.isEmpty();
    }

    public HashSet<PackageImpl> getInputDependencies() {
        return inputDependencies;
    }

    public HashSet<PackageImpl> getOutputDependencies() {
        return outputDependencies;
    }

    @Override
    public Integer getNCP() {
        return ncp;
    }

    @Override
    public Integer getNIP() {
        return nip;
    }

    @Override
    public Double getA() {
        return a;
    }

    @Override
    public Integer getAC() {
        return ac;
    }

    @Override
    public Integer getEC() {
        return ec;
    }

    @Override
    public Double getI() {
        return i;
    }

    @Override
    public Double getD() {
        return d;
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
    public Double getC() {
        return coverage;
    }

    @Override
    public Double getClassLCCMax() {
        return cLccMax;
    }

    @Override
    public Double getClassLCCMin() {
        return cLccMin;
    }

    @Override
    public Double getClassLCCAvg() {
        return cLccAvg;
    }

    @Override
    public Integer getClassLCOM1Max() {
        return cLcom1Max;
    }

    @Override
    public Integer getClassLCOM1Min() {
        return cLcom1Min;
    }

    @Override
    public Integer getClassLCOM1Avg() {
        return cLcom1Avg;
    }

    @Override
    public Integer getClassLCOM2Max() {
        return cLcom2Max;
    }

    @Override
    public Integer getClassLCOM2Min() {
        return cLcom2Min;
    }

    @Override
    public Integer getClassLCOM2Avg() {
        return cLcom2Avg;
    }

    @Override
    public Integer getClassLCOM3Max() {
        return cLcom3Max;
    }

    @Override
    public Integer getClassLCOM3Min() {
        return cLcom3Min;
    }

    @Override
    public Integer getClassLCOM3Avg() {
        return cLcom3Avg;
    }

    @Override
    public Integer getClassLCOM4Max() {
        return cLcom4Max;
    }

    @Override
    public Integer getClassLCOM4Min() {
        return cLcom4Min;
    }

    @Override
    public Integer getClassLCOM4Avg() {
        return cLcom4Avg;
    }

    @Override
    public Double getClassLCOM5Max() {
        return cLcom5Max;
    }

    @Override
    public Double getClassLCOM5Min() {
        return cLcom5Min;
    }

    @Override
    public Double getClassLCOM5Avg() {
        return cLcom5Avg;
    }

    @Override
    public Integer getClassLOCMax() {
        return cLocMax;
    }

    @Override
    public Integer getClassLOCMin() {
        return cLocMin;
    }

    @Override
    public Integer getClassLOCmMax() {
        return cLocmMax;
    }

    @Override
    public Integer getClassLOCmMin() {
        return cLocmMin;
    }

    @Override
    public Double getClassNAKMax() {
        return cNakMax;
    }

    @Override
    public Double getClassNAKMin() {
        return cNakMin;
    }

    @Override
    public Double getClassNAKAvg() {
        return cNakAvg;
    }

    @Override
    public Integer getClassNOCMax() {
        return cNocMax;
    }

    @Override
    public Integer getClassNOCMin() {
        return cNocMin;
    }

    @Override
    public Integer getClassNOCSum() {
        return cNocSum;
    }

    @Override
    public Integer getClassNOFMax() {
        return cNofMax;
    }

    @Override
    public Integer getClassNOFMin() {
        return cNofMin;
    }

    @Override
    public Integer getClassNOFSum() {
        return cNofSum;
    }

    @Override
    public Integer getClassNOMMax() {
        return cNomMax;
    }

    @Override
    public Integer getClassNOMMin() {
        return cNomMin;
    }

    @Override
    public Integer getClassNOMSum() {
        return cNomSum;
    }

    @Override
    public Integer getClassNOSFMax() {
        return cNosfMax;
    }

    @Override
    public Integer getClassNOSFMin() {
        return cNosfMin;
    }

    @Override
    public Integer getClassNOSFSum() {
        return cNosfSum;
    }

    @Override
    public Integer getClassNOSMMax() {
        return cNosmMax;
    }

    @Override
    public Integer getClassNOSMMin() {
        return cNosmMax;
    }

    @Override
    public Integer getClassNOSMSum() {
        return cNosmSum;
    }

    @Override
    public Integer getClassNTMMax() {
        return cNtmMax;
    }

    @Override
    public Integer getClassNTMMin() {
        return cNtmMin;
    }

    @Override
    public Integer getClassNTMSum() {
        return cNtmSum;
    }

    @Override
    public Double getClassTCCMax() {
        return cTccMax;
    }

    @Override
    public Double getClassTCCMin() {
        return cTccMin;
    }

    @Override
    public Double getClassTCCAvg() {
        return cTccAvg;
    }

    @Override
    public Integer getClassWMCMax() {
        return cWmcMax;
    }

    @Override
    public Integer getClassWMCSum() {
        return cWmcSum;
    }

    @Override
    public Integer getMethodLOCMax() {
        return mLocMax;
    }

    @Override
    public Integer getMethodLOCMin() {
        return mLocMin;
    }

    @Override
    public Integer getMethodLOCmMax() {
        return mLocmMax;
    }

    @Override
    public Integer getMethodLOCmMin() {
        return mLocmMin;
    }

    @Override
    public Integer getMethodNBDMax() {
        return mNbdMax;
    }

    @Override
    public Integer getMethodNBDMin() {
        return mNbdMin;
    }

    @Override
    public Integer getMethodNBDAvg() {
        return mNbdAvg;
    }

    @Override
    public Integer getMethodNOPMax() {
        return mNopMax;
    }

    @Override
    public Integer getMethodNOPMin() {
        return mNopMin;
    }

    @Override
    public Integer getMethodNOPSum() {
        return mNopSum;
    }

    @Override
    public Integer getMethodVGMax() {
        return mVgMax;
    }

    @Override
    public Integer getMethodVGMin() {
        return mVgMin;
    }

    @Override
    public Integer getMethodVGAvg() {
        return mVgAvg;
    }

    @Override
    public Integer getClassWMCMin() {
        return cWmcMin;
    }

    public boolean isTestPackage() {
        return testPackage;
    }

    public void setTestPackage(boolean testPackage) {
        this.testPackage = testPackage;
    }

    public void build() throws IOException, BuildException {
        builder.build();
    }

    /**
     * Builds the tree structure for the package and measures the metrics of 
     * the package.
     * @throws BuildException Thrown when there was a problem with building the 
     * structure.
     */
    public void measure() throws BuildException, IOException {
        if (fo.isFolder()) {
            measurer.measure();
        } else {
            String msg = "The package FileObject is not a folder";
            BuildException be = new BuildException(msg);
            logger.log(Level.SEVERE, msg, be);
            throw be;
        }
    }

    public void postMeasure() throws BuildException {
        if (fo.isFolder()) {
            for (SourceFileImpl sfi : sourceFiles.values()) {
                sfi.postMeausre();
            }

        } else {
            String msg = "The package FileObject is not a folder";
            BuildException be = new BuildException(msg);
            logger.log(Level.SEVERE, msg, be);
            throw be;
        }
    }

    public void packageMeasure() {
        postMeasurer.postMeasure();
        
        // calculation of the coverage of the package
        if (classes.size() > 0) {
            int n = 0; // number of classes covered
            int mainN = 0; // number of main classes
            for (ClassImpl ci : getClasses()) {
                if (ci.getC()) {
                    n++;
                }
                if (ci.isMainClass()) {
                    mainN++;
                }
            }
            coverage = ((double) n) / ((double) mainN);
        } else {
            coverage = 0.0;
        }
    }
    FileObject fo;
    ProjectImpl pi;
    String name;
    Boolean tests = null;
    boolean testPackage = false;
    boolean defaultPackage = false;
    TreeMap<String, SourceFileImpl> sourceFiles = new TreeMap<String, SourceFileImpl>();
    TreeMap<String, ClassImpl> classes = new TreeMap<String, ClassImpl>();
    HashSet<PackageImpl> outputDependencies = new HashSet<PackageImpl>();
    HashSet<PackageImpl> inputDependencies = new HashSet<PackageImpl>();
    Integer ncp = null;
    Integer nip = null;
    Double a = null;
    Integer ec = null;
    Integer ac = null;
    Double i = null;
    Double d = null;
    Integer loc = null, locm = null;
    Double coverage = null;
    Integer mLocmMax = null, mLocmMin = null;
    Integer mLocMax = null, mLocMin = null;
    Integer mNopMax = null, mNopMin = null, mNopSum = null;
    Integer mNbdMax = null, mNbdMin = null, mNbdAvg = null;
    Integer mVgMax = null, mVgMin = null, mVgAvg = null;
    Integer cNocMax = null, cNocMin = null, cNocSum = null;
    Double cNakMax = null, cNakMin = null, cNakAvg = null;
    Integer cNtmMax = null, cNtmMin = null, cNtmSum = null;
    Integer cLocmMax = null, cLocmMin = null;
    Integer cLocMax = null, cLocMin = null;
    Integer cWmcMax = null, cWmcMin = null, cWmcSum = null;
    Double cLccMax = null, cLccMin = null, cLccAvg = null;
    Double cTccMax = null, cTccMin = null, cTccAvg = null;
    Double cLcom5Max = null, cLcom5Min = null, cLcom5Avg = null;
    Integer cLcom4Max = null, cLcom4Min = null, cLcom4Avg = null;
    Integer cLcom3Max = null, cLcom3Min = null, cLcom3Avg = null;
    Integer cLcom2Max = null, cLcom2Min = null, cLcom2Avg = null;
    Integer cLcom1Max = null, cLcom1Min = null, cLcom1Avg = null;
    Integer cNosfMax = null, cNosfMin = null, cNosfSum = null;
    Integer cNofMax = null, cNofMin = null, cNofSum = null;
    Integer cNosmMax = null, cNosmMin = null, cNosmSum = null;
    Integer cNomMax = null, cNomMin = null, cNomSum = null;
    private PackageBuilder builder = new PackageBuilder(this);
    private PackageMeasurer measurer = new PackageMeasurer(this);
    private PostPackageMeasurer postMeasurer = new PostPackageMeasurer(this);
    private static final Logger logger = Logger.getLogger(PackageImpl.class.getName());
}
