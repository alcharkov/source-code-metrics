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
import org.openide.filesystems.FileObject;

/**
 * Represents the java language class. The class contains methods.
 * @author Krystian Warzocha
 */
public interface IClass {

    /**
     * Returns the name of the class.
     * @return The name of the class.
     */
    String getName();

    /**
     * Returns the kind of compilation unit that the object behind this 
     * interface represents. It may be inteface, class or enumaration.
     * @return The kind of compilation unit.
     */
    CompilationUnitKind getCompilationUnitKind();

    /**
     * Returns the file object which contains the given class.
     * @return FileObject containing the given class.
     */
    FileObject getFileObject();

    /**
     * Returns all of the methods that the class has.
     * @return All of the methods of the class.
     */
    Collection<IMethod> getMethods();

    /**
     * Returns the number of methods of the class.
     * @return Number of methods of the class.
     */
    Integer getNOM();

    /**
     * Returns the number of static methods of this class.
     * @return Number of static methods of the class.
     */
    Integer getNOSM();

    /**
     * Returns the number of fields of the class.
     * @return Number of fields of the class.
     */
    Integer getNOF();

    /**
     * Returns the number of static fields of the class.
     * @return Number of static fields.
     */
    Integer getNOSF();

    /**
     * Returns the Lack of cohesion in methods 1.
     * @return LCOM1
     */
    Integer getLCOM1();

    /**
     * Returns the lack of cohesion in methods 2.
     * @return LCOM2
     */
    Integer getLCOM2();

    /**
     * Returns the lack of cohesion in methods 3.
     * @return LCOM3
     */
    Integer getLCOM3();

    /**
     * Returns the lack of cohesion in methods 4.
     * @return LCOM4
     */
    Integer getLCOM4();

    /**
     * Returns the Lack of Cohesion in Methods 5.
     * @return LCOM5
     */
    Double getLCOM5();

    /**
     * Returns the tight class coupling.
     * @return TCC
     */
    Double getTCC();

    /**
     * Returns the loose class coupling.
     * @return LCC
     */
    Double getLCC();

    /**
     * Returns the weighted methods per class.
     * @return WMC
     */
    Integer getWMC();

    /**
     * Retrurn the lines of code of this class.
     * @return Number of lines of code of this class.
     */
    Integer getLOC();

    /**
     * Returns number of lines of comments of this class.
     * @return Number of lines of comments of this class.
     */
    Integer getLOCm();

    /**
     * Returns the number of test methods.
     * @return Number of Test Methods.
     */
    Integer getNTM();

    /**
     * Returns the number of asserts per kilo lines of code.
     * @return Number of asserts per kilo lines of code.
     */
    Double getNAK();

    /**
     * Returns the number of children of the class.
     * @return Number Of Children.
     */
    Integer getNOC();

    /**
     * Tests coverage of the class.
     * @return Coverage.
     */
    Boolean getC();

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
     * Average cyclomatic complexities of the methods.
     * @return Average VG.
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
     * Average NBD of the methods.
     * @return Average NBD.
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
     * Sum of all of the NOP of the methods.
     * @return Sum of the NOP.
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
