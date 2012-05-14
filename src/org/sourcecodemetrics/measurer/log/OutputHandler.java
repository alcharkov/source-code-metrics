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

package org.sourcecodemetrics.measurer.log;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 * This is a logs handler. It is responsible for sending the logs to the 
 * NetBeans output window.
 * @author Krystian Warzocha
 */
public class OutputHandler extends Handler {

    /**
     * Opens the new output window.
     */
    public OutputHandler() {
        if (io == null) {
            io = IOProvider.getDefault().getIO("Metrics measurement", true);
            io.select();
            io.setFocusTaken(true); // takes focus when anything is written to it
        }

        sb = new StringBuilder();
    }

    @Override
    public void publish(LogRecord record) {
        sb.delete(0, sb.length()); // clear the string builder
        sb.append(record.getLevel().getName()).append(" ");
//        sb.append(record.getSourceClassName()).append(".");
//        sb.append(record.getSourceMethodName()).append(" ");
        sb.append(record.getMessage());
        io.getOut().println(sb.toString());
    }

    @Override
    public void flush() {
    }

    public void reset() throws IOException {
        io.getOut().reset();
    }

    @Override
    public void close() throws SecurityException {
    }
    private InputOutput io = null;
    private StringBuilder sb = null;
}
