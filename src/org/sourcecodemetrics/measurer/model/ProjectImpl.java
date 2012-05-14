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

package org.sourcecodemetrics.measurer.model;

import org.sourcecodemetrics.measurer.model.packages.PackageImpl;
import org.sourcecodemetrics.measurer.api.BuildException;
import org.sourcecodemetrics.measurer.api.IPackage;
import org.sourcecodemetrics.measurer.api.IProject;
import org.sourcecodemetrics.measurer.api.MeasurementException;
import org.sourcecodemetrics.measurer.model.classes.ClassImpl;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.filesystems.FileObject;

/**
 * 
 * @author Krystian Warzocha
 */
public class ProjectImpl implements IProject {

    private Iterable<IPackage> packageImpls;

    /**
     * Injects the directory to the project.
     * @param fo The top directory of the project. It cannot be null.
     */
    public ProjectImpl(FileObject fo) {
        this.fo = fo;
    }

    public void initlize() {
        name = fo.getName(); // name of the project is the name of the top directory
    }

    public static ProjectImpl getAnalyzedProject() {
        return analyzedProject;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<IPackage> getPackages() {
        return (Collection<IPackage>) (Collection<?>) packages.values();
    }

    public Map<String, PackageImpl> getPackagesMap() {
        return packages;
    }

    public Map<String, PackageImpl> getIdentifiersMap() {
        return identifiers;
    }

    public Map<String, ClassImpl> getClassesMap() {
        return classes;
    }

    @Override
    public Integer getLOC() {
        return loc;
    }

    public FileObject getTest() {
        return test;
    }

    @Override
    public Integer getLOCm() {
        return locm;
    }

    @Override
    public Integer getPackageLocMax() {
        return pLocMax;
    }

    @Override
    public Integer getPackageLocMin() {
        return pLocMin;
    }

    @Override
    public Integer getPackageLocmMax() {
        return pLocmMax;
    }

    @Override
    public Integer getPackageLocmMin() {
        return pLocmMin;
    }

    @Override
    public Double getPackageAAvg() {
        return pAAvg;
    }

    @Override
    public Integer getPackageACAvg() {
        return pAcAvg;
    }

    @Override
    public Double getPackageCoverageAvg() {
        return pCAvg;
    }

    @Override
    public Double getPackageDAvg() {
        return pDAvg;
    }

    @Override
    public Integer getPackageECAvg() {
        return pEcAvg;
    }

    @Override
    public Double getPackageIAvg() {
        return pIAvg;
    }

    @Override
    public Integer getPackageNCPSum() {
        return pNcpSum;
    }

    @Override
    public Integer getPackageNIPSum() {
        return pNipSum;
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
        return cNosmMin;
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
        return mNopMax;
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
    public Integer getPackageACMax() {
        return pAcMax;
    }

    @Override
    public Integer getPackageACMin() {
        return pAcMin;
    }

    @Override
    public Double getPackageAMax() {
        return pAMax;
    }

    @Override
    public Double getPackageAMin() {
        return pAMin;
    }

    @Override
    public Double getPackageCoverageMax() {
        return pCMax;
    }

    @Override
    public Double getPackageCoverageMin() {
        return pCMin;
    }

    @Override
    public Double getPackageDMax() {
        return pDMax;
    }

    @Override
    public Double getPackageDMin() {
        return pDMin;
    }

    @Override
    public Integer getPackageECMax() {
        return pEcMax;
    }

    @Override
    public Integer getPackageECMin() {
        return pEcMin;
    }

    @Override
    public Double getPackageIMax() {
        return pIMax;
    }

    @Override
    public Double getPackageIMin() {
        return pIMin;
    }

    @Override
    public Integer getPackageNCPMax() {
        return pNcpMax;
    }

    @Override
    public Integer getPackageNCPMin() {
        return pNcpMin;
    }

    @Override
    public Integer getPackageNIPMax() {
        return pNipMax;
    }

    @Override
    public Integer getPackageNIPMin() {
        return pNipMin;
    }

    @Override
    public Integer getClassWMCMin() {
        return cWmcMin;
    }

    public FileObject getSrc() {
        return src;
    }

    public void build() throws BuildException, IOException {
        // make sure that it points to the directory
        if (fo.isFolder()) {

            // find the src directory where the java sources are located
            src = null;
            for (Enumeration<? extends FileObject> e = fo.getFolders(true); e.hasMoreElements();) {
                FileObject subDir = e.nextElement();
                if ("src".equals(subDir.getName())) {
                    src = subDir;
                    break;
                }
            }

            // find the test directory where the junit tests are located
            test = null;
            for (Enumeration<? extends FileObject> e = fo.getFolders(true); e.hasMoreElements();) {
                FileObject subDir = e.nextElement();
                if ("test".equals(subDir.getName())) {
                    test = subDir;
                    break;
                }
            }

            // make sure that the src directory has been found
            if (src == null) {
                String message = "The src directory could not be found.";
                BuildException be = new BuildException(message);
                logger.log(Level.WARNING, message, be);
                throw be;
            }

            // iterate over all of the folders/packages recursively
            for (Enumeration<? extends FileObject> e = src.getFolders(true); e.hasMoreElements();) {
                FileObject subPackage = e.nextElement();
                PackageImpl packageImpl = new PackageImpl(subPackage, this, false, false);
                packageImpl.initlize();

                // svn directories do not count
                if (!packageImpl.getName().toLowerCase().contains(".svn")) {
                    if (!packageImpl.getName().toLowerCase().contains(".cvs")) {
                        packages.put(packageImpl.getName(), packageImpl); // add packege to the set of all of the packages
                        logger.info("Found package " + packageImpl.getName());
                    }
                }
            }

            // iterate over tests 
            if (test != null) {

                for (Enumeration<? extends FileObject> e = test.getFolders(true); e.hasMoreElements();) {
                    FileObject subPackage = e.nextElement();
                    PackageImpl packageImpl = new PackageImpl(subPackage, this, false, true);
                    packageImpl.initlize();

                    // svn directories do not count
                    if (!packageImpl.getName().toLowerCase().contains(".svn")) {
                        if (!packageImpl.getName().toLowerCase().contains(".cvs")) {
                            packages.put(packageImpl.getName(), packageImpl); // add packege to the set of all of the packages
                            packageImpl.setTestPackage(true);
                            logger.info("Found package " + packageImpl.getName());
                        }
                    }
                }
            }

            // adding the default package
            PackageImpl defaultPackage = new PackageImpl(src, this, true, false);
            defaultPackage.initlize();
            packages.put(defaultPackage.getName(), defaultPackage); // add package to the set of all of the packages

            // adding the default tests package
            if (test != null) {
                PackageImpl defaultTestsPackage = new PackageImpl(test, this, true, true);
                defaultTestsPackage.initlize();
                packages.put(defaultTestsPackage.getName(), defaultTestsPackage); // add package to the set of all of the packages
            }

            // building all of the source files in this package
            for (PackageImpl pi : packages.values()) {
                pi.build();
                
                if(!pi.getClasses().isEmpty() && !pi.isTestPackage() && !pi.isTests()) {
                    numberOfPackages++;
                }

                // put all of the classes to the map of identifiers
                for (ClassImpl classImpl : pi.getClasses()) {
                    identifiers.put(classImpl.getFullName(), pi);
                    classes.put(classImpl.getFullName(), classImpl);
                }

                // put the package itself to the list of identifiers
                identifiers.put(pi.getName(), pi);
            }

            logger.info(name + " project contains " + packages.size() + " packages.");

        } else {
            String message = "The given top directory is not a directory. Terminating measurement.";
            BuildException be = new BuildException(message);
            logger.log(Level.WARNING, message, be);
            throw be;
        }
    }

    /**
     * Performs all of the measurements associated with the project.
     */
    public void measure() throws MeasurementException, BuildException, IOException {
        if (fo.isFolder()) {

            // command every package to measure metrics
            for (PackageImpl packageImpl : packages.values()) {
                packageImpl.measure();

                if (packageImpl.isValid()) {
                    packages.put(packageImpl.getName(), packageImpl); // adding the package only if it should be
                    logger.info("Package " + packageImpl.getName() + " is a valid package contains java files.");
                }
            }

            // command every package to measure metrics
            for (PackageImpl packageImpl : packages.values()) {
                packageImpl.postMeasure();
            }

            // command every package to measure metrics which depend on the outcome of previous measurements
            for (PackageImpl packageImpl : packages.values()) {
                packageImpl.packageMeasure();
            }

            measurer.measure();

        } else {
            String message = "The given top directory is not a directory. Terminating measurement.";
            MeasurementException e = new MeasurementException(message);
            logger.log(Level.WARNING, message, e);
            throw e;
        }
    }
    TreeMap<String, PackageImpl> packages = new TreeMap<String, PackageImpl>();
    /**
     * Maps all possible identifiers of the packages and classes mapped to the 
     * package they belong to.
     */
    private HashMap<String, PackageImpl> identifiers = new HashMap<String, PackageImpl>();
    private Map<String, ClassImpl> classes = new HashMap<String, ClassImpl>();
    private String name = "";
    private FileObject fo;
    private FileObject src;
    private FileObject test;
    Integer numberOfPackages = 0;
    Integer loc = null;
    Integer locm = null;
    Integer pNcpMax = null, pNcpMin = null, pNcpSum = null;
    Integer pNipMax = null, pNipMin = null, pNipSum = null;
    Double pAMax = null, pAMin = null, pAAvg = null;
    Integer pEcMax = null, pEcMin = null, pEcAvg = null;
    Integer pAcMax = null, pAcMin = null, pAcAvg = null;
    Double pIMax = null, pIMin = null, pIAvg = null;
    Double pDMax = null, pDMin = null, pDAvg = null;
    Double pCMax = null, pCMin = null, pCAvg = null;
    Integer pLocMax = null, pLocMin = null;
    Integer pLocmMax = null, pLocmMin = null;
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
    private ProjectMeasurer measurer = new ProjectMeasurer(this);
    private static ProjectImpl analyzedProject = null;
    private static final Logger logger = Logger.getLogger(ProjectImpl.class.getName());
}
