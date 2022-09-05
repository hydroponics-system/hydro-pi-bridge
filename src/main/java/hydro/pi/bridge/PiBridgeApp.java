package hydro.pi.bridge;

import hydro.pi.bridge.api.domain.UserAuthToken;
import hydro.pi.bridge.api.service.SystemAuthenticationManager;
import hydro.pi.bridge.common.logger.LoggerConfig;
import hydro.pi.bridge.environment.PiBridgeEnvironmentService;
import hydro.pi.bridge.susbcription.client.SubscriptionClient;
import hydro.pi.bridge.susbcription.listeners.GeneralNotficationListener;
import hydro.pi.bridge.susbcription.service.SubscriptionListeners;

/**
 * Main Application to run
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class PiBridgeApp {

    public static void main(String[] args) throws Exception {
        start(args);
        runForever();
    }

    /**
     * Method for showing methods that need to start as soon as the application
     * begings.
     * 
     * @param args The arguements to evaluate.
     * @throws Exception
     */
    private static void start(String[] args) throws Exception {
        PiBridgeEnvironmentService.setActiveEnvironment(args);
        LoggerConfig.init();
        startSubscription(authenticate());
    }

    /**
     * Abstract method to trigger a run forever application.
     */
    private static void runForever() {
        while (true) {
        }
    }

    private static String authenticate() throws Exception {
        SystemAuthenticationManager systemAuth = new SystemAuthenticationManager();
        UserAuthToken user = systemAuth.userAuthentication();
        return String.format("%s?%s", PiBridgeEnvironmentService.active().socket(), user.getToken());
    }

    private static void startSubscription(String wsUrl) {
        SubscriptionClient client = new SubscriptionClient();
        client.connectAsync(wsUrl).subscribe(res -> addListeners(client));
    }

    private static void addListeners(SubscriptionClient client) {
        SubscriptionListeners listeners = new SubscriptionListeners(client);
        listeners.register(new GeneralNotficationListener());
    }
}
