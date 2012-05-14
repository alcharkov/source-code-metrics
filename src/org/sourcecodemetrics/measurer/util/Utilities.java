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

package org.sourcecodemetrics.measurer.util;

import java.math.BigDecimal;

/**
 * Class contains some mathematical utility methods.
 * @author Krystian Warzocha
 */
public class Utilities {
    
    /**
     * Method computes the factorial of the given integer.
     * @param n The integer whose factorial shall be computed.
     * @return The factorial of a given integer.
     */
    public static BigDecimal factorial(int n) {
        BigDecimal result = BigDecimal.valueOf(1l);
        while(n > 0) {
            BigDecimal bd = new BigDecimal(n);
            result = result.multiply(bd);
            n--;
        }
        return result;
    }
    
    
}
