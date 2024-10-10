package dtcc.itn262.utilities.gamecore;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GameLogger {
    private static final Logger logger = Logger.getLogger("GameLogger");

    private GameLogger() {
        throw new IllegalStateException("Utility class");
    }

    static {
        try {
            FileHandler fh = new FileHandler("errors.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);  // Disable console logging
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Log severe error
    public static void logError(String message) {
        logger.severe(message);
    }

    // Log warning
    public static void logWarning(String message) {
        logger.warning(message);
    }

    // Log info
    public static void logInfo(String message) {
        logger.info(message);
    }

    // Log debug
    public static void logDebug(String message) {
        logger.log(Level.FINE, message);
    }
}
