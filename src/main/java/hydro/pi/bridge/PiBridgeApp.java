package hydro.pi.bridge;

import ch.qos.logback.classic.Level;
import hydro.pi.bridge.common.logger.LoggerConfig;
import hydro.pi.bridge.environment.PiBridgeEnvironmentService;
import hydro.pi.bridge.system.client.HydroSystemClient;

/**
 * Main Application to run
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class PiBridgeApp {

    private static final HydroSystemClient hydroSystemClient = new HydroSystemClient();

    public static void main(String[] args) throws Exception {
        init(args);
        runForever();
    }

    /**
     * Method for showing methods that need to initalize as soon as the application
     * begins.
     * 
     * @param args The arguements to evaluate.
     * @throws Exception If an error occurred starting the system.
     */
    private static void init(String[] args) throws Exception {
        PiBridgeEnvironmentService.setActiveEnvironment(args);
        LoggerConfig.configure(Level.INFO);
        hydroSystemClient.start();
    }

    /**
     * Abstract method to trigger a run forever application.
     */
    private static void runForever() {
        while(true) {}
    }
}
