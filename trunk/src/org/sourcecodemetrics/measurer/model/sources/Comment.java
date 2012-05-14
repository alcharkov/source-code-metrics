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

/**
 * Represents a comment. It can be either single line or multiline comment in
 * a java source file.
 * @author Krystian Warzocha
 */
public class Comment {

    public Comment(int start, int end, int lines) {
        this.start = start;
        this.end = end;
        this.lines = lines;
    }

    public int getEnd() {
        return end;
    }

    public int getLines() {
        return lines;
    }

    public int getStart() {
        return start;
    }

    /**
     * Determines whether this comment contains the sequence of characters with 
     * indexes between start and end.
     * @param start Start index of the sequence of characters.
     * @param end End index of the sequence of characters.
     * @return True if the sequence of characters is contained inside the 
     * comment. False otherwise.
     */
    public boolean contains(int start, int end) {
        if (this.start <= start && end <= this.end) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines whether this comment is contained inside the sequence of 
     * characters which are between start and end indexes.
     * @param start Start index of the sequence of characters.
     * @param end End index of the sequence of characters.
     * @return True if the sequence of characters contains this comments. False 
     * otherwise.
     */
    public boolean isContained(int start, int end) {
        if (start <= this.start && this.end <= end) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.start != other.start) {
            return false;
        }
        if (this.end != other.end) {
            return false;
        }
        if (this.lines != other.lines) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.start;
        hash = 79 * hash + this.end;
        hash = 79 * hash + this.lines;
        return hash;
    }
    private int start;
    private int end;
    private int lines;
}
