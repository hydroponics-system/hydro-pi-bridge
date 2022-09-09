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
        PiBridgeEnvironmentService.setActiveEnvironment(args);
        LoggerConfig.configure(Level.INFO);
        hydroSystemClient.start();
        runForever();
    }

    /**
     * Abstract method to trigger a run forever application.
     */
    private static void runForever() {
        while(true) {}
    }
}
