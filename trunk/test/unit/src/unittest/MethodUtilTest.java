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

package unittest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.sourcecodemetrics.measurer.util.MethodUtil;
import org.sourcecodemetrics.measurer.util.MethodUtil;
import org.sourcecodemetrics.measurer.util.MethodUtil;
import static org.junit.Assert.*;

/**
 *
 * @author Krystian
 */
public class MethodUtilTest {

    /**
     * Test of buildMethodSignature method, of class MethodUtil.
     */
    @Test
    public void testBuildMethodSignature() {
        System.out.println("buildMethodSignature");
        String name = "method";
        List<String> types = new ArrayList<String>(Arrays.asList("int", "char", "String"));
        String expResult = "method(int, char, String)";
        String result = MethodUtil.buildMethodSignature(name, types);
        assertEquals("The method signature has been build incorrectly.", expResult, result);
    }

    /**
     * Test of belongsToPackages method, of class MethodUtil.
     */
    @Test
    public void testBelongsToPackages() {
//        System.out.println("belongsToPackages");
//        String identifier = "part1.part2.part3.class1";
//        List<String> packages1 = new ArrayList<String>(Arrays.asList("part1", "part1.part2", "bla bla", "part1.part2.part3"));
//        List<String> packages2 = new ArrayList<String>(Arrays.asList("part1", "part1.part2", "bla bla", "part1.part2.part3"));
//        boolean expResult = true;
//        boolean result = MethodUtil.belongsToPackages(identifier, packages);
//        assertEquals("", expResult, result);
    }
}
