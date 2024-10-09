package dtcc.itn262.utilities.gamecore;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GameLogger {
    GameLogger(){
        // Empty constructor
    }
    private static final Logger logger = Logger.getLogger("GameLogger");

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

    public static void logError(String message) {
        logger.severe(message);  // Logs at SEVERE level
    }
}