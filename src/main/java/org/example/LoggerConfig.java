package org.example;

import java.io.IOException;
import java.util.logging.*;

public class LoggerConfig {
    private static final Logger logger = Logger.getLogger("org.example");

    static {
        try {
            LogManager.getLogManager().reset();
            logger.setLevel(Level.ALL);

            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            logger.addHandler(consoleHandler);

            // File handler
            FileHandler fileHandler = new FileHandler("realEstateApp.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to initialize logger file handler", e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
