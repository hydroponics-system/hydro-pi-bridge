package hydro.pi.bridge.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gets the Pi Bridge Environment that is currently running.
 * 
 * @author Sam Butler
 * @since September 4, 2022
 */
public class PiBridgeEnvironmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PiBridgeEnvironmentService.class);
    private static final String ACTIVE_ENVIRONMENT = "PI_ENVIRONMENT";

    /**
     * Will set the currently running environment that is running
     * on the machine.
     * 
     * @param args The arguments to check and validate.
     */
    public static void setActiveEnvironment(String[] args) {
        PiEnvironment env = args.length >= 1 ? PiEnvironment.get(args[0]) : PiEnvironment.LOCAL;

        LOGGER.info("Active Pi Bridge Environment: {}", env);
        System.setProperty(ACTIVE_ENVIRONMENT, env.tag());
    }

    /**
     * Gets the active environment that is currently running by checking the passed
     * arguements.
     * 
     * @param args The arguements to check.
     * @return Returns the active pi environment.
     */
    public static PiEnvironment active() {
        return PiEnvironment.get(System.getProperty(ACTIVE_ENVIRONMENT));
    }
}
