package hydro.pi.bridge.common.logger;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

/**
 * Configuration class for the logger level.
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class LoggerConfig {

    /**
     * Sets the logger level to the passed in leve.
     * 
     * @param l The log level to set.
     */
    public static void configure(Level l) {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(l);
    }
}
