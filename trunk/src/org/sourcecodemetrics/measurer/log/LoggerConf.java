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
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Configures the logging of the Measurer module.
 * @author Krystian Warzocha.
 */
public class LoggerConf {

    /**
     * Sets up the logger to the default configuration.
     */
    static public void setup() {
        Logger logger = Logger.getLogger("org.sourcecodemetrics");
        
        logger.setLevel(Level.FINER);

        consoleHandler.setLevel(Level.FINER);
        logger.removeHandler(consoleHandler);
        logger.addHandler(consoleHandler);

        outputHandler.setLevel(Level.FINER);
        logger.removeHandler(outputHandler);
        logger.addHandler(outputHandler);
    }

    static public void reset() throws IOException {
        outputHandler.reset();
    }

    /**
     * Sets up the logger to the level requested by the user. 
     * @param level The logging level.
     */
    static public void setLevel(Level level) {
        Logger logger = Logger.getLogger("org.sourcecodemetrics");
        logger.setLevel(level);

        consoleHandler.setLevel(level);
        logger.removeHandler(consoleHandler);
        logger.addHandler(consoleHandler);

        outputHandler.setLevel(level);
        logger.removeHandler(outputHandler);
        logger.addHandler(outputHandler);
    }
    static private ConsoleHandler consoleHandler = new ConsoleHandler();
    static private OutputHandler outputHandler = new OutputHandler();
}
