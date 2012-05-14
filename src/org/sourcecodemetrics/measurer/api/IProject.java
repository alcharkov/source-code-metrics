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

package org.sourcecodemetrics.measurer.api;

import java.util.Collection;

/**
 * Represents the java project. The project has the following major attributes:
 * <ul>
 * <li>name
 * <li>packages it contains
 * </ul>
 * @author Krystian Warzocha
 */
public interface IProject {

    /**
     * Used to access the name of the project.
     * @return The name of the project. 
     */
    String getName();

    /**
     * Returns all of the packages belonging to the project.
     * @return All of the packages belonging to the project.
     */
    Collection<IPackage> getPackages();

    /**
     * Returns the total number of lines.
     * @return LOC.
     */
    Integer getLOC();

    /**
     * Returns the total number of lines of comments.
     * @return LOC.
     */
    Integer getLOCm();

    /**
     * Returns the maximal number of classes in the package.
     * @return Maximal number of classes in the package.
     */
    Integer getPackageNCPMax();

    /**
     * Returns the minimal number of classes in the package.
     * @return Minimal number of classes in the package.
     */
    Integer getPackageNCPMin();
    
    /**
     * Returns the sum of the number of classes in the package.
     * @return Sum of the number of classes in the package.
     */
    Integer getPackageNCPSum();

    /**
     * Returns the maximal number of interfaces in the package.
     * @return Maximal number of interface in the package.
     */
    Integer getPackageNIPMax();

    /**
     * Returns the minimal number of interfaces in the package.
     * @return Minimal number of interface in the package.
     */
    Integer getPackageNIPMin();
    
    /**
     * Returns the sum of the number of interfaces in the package.
     * @return Sum of the number of interface in the package.
     */
    Integer getPackageNIPSum();

    /**
     * Returns the maximal abstractness of the package.
     * @return Maximal abstractness of the package.
     */
    Double getPackageAMax();

    /**
     * Returns the minimal abstractness of the package.
     * @return Minimal abstractness of the package.
     */
    Double getPackageAMin();
    
    /**
     * Returns the average abstractness of the package.
     * @return Average abstractness of the package.
     */
    Double getPackageAAvg();

    /**
     * Returns the maximal efferent coupling of the package.
     * @return Maximal efferent coupling of the package.
     */
    Integer getPackageECMax();

    /**
     * Returns the minimal efferent coupling of the package.
     * @return Minimal efferent coupling of the package.
     */
    Integer getPackageECMin();
    
    /**
     * Returns the average efferent coupling of the package.
     * @return Average efferent coupling of the package.
     */
    Integer getPackageECAvg();

    /**
     * Returns the maximal afferent coupling of the package.
     * @return Maximal afferent coupling of the package.
     */
    Integer getPackageACMax();

    /**
     * Returns the minimal afferent coupling of the package.
     * @return Minimal afferent coupling of the package.
     */
    Integer getPackageACMin();
    
    /**
     * Returns the average afferent coupling of the package.
     * @return Average afferent coupling of the package.
     */
    Integer getPackageACAvg();

    /**
     * Returns the maximal instability of the package.
     * @return Maximal instability of the package.
     */
    Double getPackageIMax();

    /**
     * Returns the minimal instability of the package.
     * @return Minimal instability of the package.
     */
    Double getPackageIMin();
    
    /**
     * Returns the average instability of the package.
     * @return Average instability of the package.
     */
    Double getPackageIAvg();

    /**
     * Returns the maximum distance of the package.
     * @return Maximum distance of the package.
     */
    Double getPackageDMax();

    /**
     * Returns the minimum distance of the package.
     * @return Minimum distance of the package.
     */
    Double getPackageDMin();
    
    /**
     * Returns the average distance of the package.
     * @return Average distance of the package.
     */
    Double getPackageDAvg();

    /**
     * Returns the maximum coverage of the package.
     * @return Maximum coverage of the package.
     */
    Double getPackageCoverageMax();

    /**
     * Returns the minimum coverage of the package.
     * @return Minimum coverage of the package.
     */
    Double getPackageCoverageMin();
    
    /**
     * Returns the average coverage of the package.
     * @return Average coverage of the package.
     */
    Double getPackageCoverageAvg();

    /**
     * Returns the maximum LOC of the package.
     * @return Maximum LOC of the package.
     */
    Integer getPackageLocMax();

    /**
     * Returns the minimum LOC of the package.
     * @return Minimum LOC of the package.
     */
    Integer getPackageLocMin();

    /**
     * Returns the maximum LOCm of the package.
     * @return Maximum LOCm of the package.
     */
    Integer getPackageLocmMax();

    /**
     * Returns the minimum LOCm of the package.
     * @return Minimum LOCm of the package.
     */
    Integer getPackageLocmMin();

    /**
     * Returns the maximal number of methods of the class.
     * @return Maximal number of methods of the class.
     */
    Integer getClassNOMMax();

    /**
     * Returns the minimal number of methods of the class.
     * @return Minimal number of methods of the class.
     */
    Integer getClassNOMMin();
    
    /**
     * Returns the total number of methods of the class.
     * @return Total number of methods of the class.
     */
    Integer getClassNOMSum();

    /**
     * Returns the maximal number of static methods of this class.
     * @return maximal number of static methods of the class.
     */
    Integer getClassNOSMMax();

    /**
     * Returns the minimal number of static methods of this class.
     * @return Minimal number of static methods of the class.
     */
    Integer getClassNOSMMin();
    
    /**
     * Returns the total number of static methods of this class.
     * @return Total number of static methods of the class.
     */
    Integer getClassNOSMSum();

    /**
     * Returns the maximal number of fields of the class.
     * @return Maximal number of fields of the class.
     */
    Integer getClassNOFMax();

    /**
     * Returns the minimal number of fields of the class.
     * @return Minimal number of fields of the class.
     */
    Integer getClassNOFMin();
    
    /**
     * Returns the total number of fields of the class.
     * @return Total number of fields of the class.
     */
    Integer getClassNOFSum();

    /**
     * Returns the maximal number of static fields of the class.
     * @return Maximal number of static fields.
     */
    Integer getClassNOSFMax();
    
    /**
     * Returns the total number of static fields of the class.
     * @return Total number of static fields.
     */
    Integer getClassNOSFSum();

    /**
     * Returns the minimal number of static fields of the class.
     * @return minimal number of static fields.
     */
    Integer getClassNOSFMin();

    /**
     * Returns the maximal lack of cohesion in methods 1.
     * @return Maximal LCOM1
     */
    Integer getClassLCOM1Max();

    /**
     * Returns the minimal lack of cohesion in methods 1.
     * @return Minimal LCOM1
     */
    Integer getClassLCOM1Min();

    /**
     * Returns the average lack of cohesion in methods 1.
     * @return Average LCOM1
     */
    Integer getClassLCOM1Avg();
    
    /**
     * Returns the maximal lack of cohesion in methods 2.
     * @return Maximal LCOM2
     */
    Integer getClassLCOM2Max();

    /**
     * Returns the minimal lack of cohesion in methods 2.
     * @return Minimal LCOM2
     */
    Integer getClassLCOM2Min();

    /**
     * Returns the average lack of cohesion in methods 2.
     * @return Average LCOM2
     */
    Integer getClassLCOM2Avg();

    /**
     * Returns the maximal lack of cohesion in methods 3.
     * @return Maximal LCOM3
     */
    Integer getClassLCOM3Max();

    /**
     * Returns the minimal lack of cohesion in methods 3.
     * @return Minimal LCOM3
     */
    Integer getClassLCOM3Min();

    /**
     * Returns the average lack of cohesion in methods 3.
     * @return Average LCOM3
     */
    Integer getClassLCOM3Avg();

    /**
     * Returns the maximal lack of cohesion in methods 4.
     * @return Maximal LCOM4
     */
    Integer getClassLCOM4Max();

    /**
     * Returns the minimal lack of cohesion in methods 4.
     * @return Minimal LCOM4
     */
    Integer getClassLCOM4Min();

    /**
     * Returns the average lack of cohesion in methods 4.
     * @return Average LCOM4
     */
    Integer getClassLCOM4Avg();

    /**
     * Returns the Maximal Lack of Cohesion in Methods 5.
     * @return Maximal LCOM5
     */
    Double getClassLCOM5Max();

    /**
     * Returns the Minimal Lack of Cohesion in Methods 5.
     * @return Minimal LCOM5
     */
    Double getClassLCOM5Min();

    /**
     * Returns the average Lack of Cohesion in Methods 5.
     * @return Average LCOM5
     */
    Double getClassLCOM5Avg();

    /**
     * Returns the maximal tight class coupling.
     * @return Maximal TCC
     */
    Double getClassTCCMax();

    /**
     * Returns the minimal tight class coupling.
     * @return Minimal TCC
     */
    Double getClassTCCMin();

    /**
     * Returns the average tight class coupling.
     * @return Average TCC
     */
    Double getClassTCCAvg();

    /**
     * Returns the maximal loose class coupling.
     * @return Maximal LCC
     */
    Double getClassLCCMax();

    /**
     * Returns the minimal loose class coupling.
     * @return Minimal LCC
     */
    Double getClassLCCMin();

    /**
     * Returns the average loose class coupling.
     * @return Average LCC
     */
    Double getClassLCCAvg();

    /**
     * Returns the maximal weighted methods per class.
     * @return Maximal WMC
     */
    Integer getClassWMCMax();

    /**
     * Returns the minimal weighted methods per class.
     * @return Minimal WMC
     */
    Integer getClassWMCMin();

    /**
     * Returns the sum of weighted methods per class.
     * @return Sum of WMC
     */
    Integer getClassWMCSum();

    /**
     * Returns the maximal lines of code of this class.
     * @return Maximal number of lines of code of this class.
     */
    Integer getClassLOCMax();

    /**
     * Returns the minimal lines of code of this class.
     * @return Minimal number of lines of code of this class.
     */
    Integer getClassLOCMin();

    /**
     * Returns maximal number of lines of comments of this class.
     * @return Maximal Number of lines of comments of this class.
     */
    Integer getClassLOCmMax();

    /**
     * Returns minimal number of lines of comments of this class.
     * @return Minimal Number of lines of comments of this class.
     */
    Integer getClassLOCmMin();

    /**
     * Returns the maximal number of test methods.
     * @return Maximal Number of Test Methods.
     */
    Integer getClassNTMMax();

    /**
     * Returns the minimal number of test methods.
     * @return Minimal Number of Test Methods.
     */
    Integer getClassNTMMin();

    /**
     * Returns the sum of number of test methods.
     * @return Average of the Number of Test Methods.
     */
    Integer getClassNTMSum();
    
    /**
     * Returns the maximal number of asserts per kilo lines of code.
     * @return Maximal Number of asserts per kilo lines of code.
     */
    Double getClassNAKMax();

    /**
     * Returns the minimal number of asserts per kilo lines of code.
     * @return Minimal Number of asserts per kilo lines of code.
     */
    Double getClassNAKMin();

    /**
     * Returns the average number of asserts per kilo lines of code.
     * @return Average Number of asserts per kilo lines of code.
     */
    Double getClassNAKAvg();
    
    /**
     * Returns the maximal number of children of the class.
     * @return Maximal Number Of Children.
     */
    Integer getClassNOCMax();

    /**
     * Returns the minimal number of children of the class.
     * @return Minkmal Number Of Children.
     */
    Integer getClassNOCMin();

    /**
     * Returns the sum of number of children of the class.
     * @return Sum of the Number Of Children.
     */
    Integer getClassNOCSum();

    /**
     * Maximal cyclomatic complexity.
     * @return Maximal cyclomatic complexity.
     */
    Integer getMethodVGMax();

    /**
     * Maximal cyclomatic complexity.
     * @return Maximal cyclomatic complexity.
     */
    Integer getMethodVGMin();

    /**
     * Average cyclomatic complexity.
     * @return Average cyclomatic complexity.
     */
    Integer getMethodVGAvg();

    /**
     * Maximal Nested Block Depth.
     * @return Maximal Nested Block Depth.
     */
    Integer getMethodNBDMax();

    /**
     * Maximal Nested Block Depth.
     * @return Maximal Nested Block Depth.
     */
    Integer getMethodNBDMin();

    /**
     * Average Nested Block Depth.
     * @return Average Nested Block Depth.
     */
    Integer getMethodNBDAvg();

    /**
     * Maximal Number Of Parameters.
     * @return Maximal Number Of Parameters.
     */
    Integer getMethodNOPMax();

    /**
     * Maximal Number Of Parameters.
     * @return Minimal Number Of Parameters.
     */
    Integer getMethodNOPMin();

    /**
     * Sum of the Number Of Parameters.
     * @return Sum of the Number Of Parameters.
     */
    Integer getMethodNOPSum();
    
    /**
     * Maximal LOC of the method.
     * @return LOC of biggest method.
     */
    Integer getMethodLOCMax();

    /**
     * Minimal LOC of the method.
     * @return LOC of the smallest method.
     */
    Integer getMethodLOCMin();

    /**
     * Maximal LOC of comments of the method.
     * @return LOC of comments of biggest method.
     */
    Integer getMethodLOCmMax();

    /**
     * Minimal LOC of comments of the method.
     * @return LOC of comments of the smallest method.
     */
    Integer getMethodLOCmMin();
}
