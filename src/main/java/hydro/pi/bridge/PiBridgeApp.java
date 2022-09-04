package hydro.pi.bridge;

import hydro.pi.bridge.environment.PiBridgeEnvironmentService;
import hydro.pi.bridge.logger.LoggerConfig;
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
    private static final String url = "ws://localhost:8000/subscription/socket?eyJhbGciOiJIUzUxMiJ9.eyJ3ZWJSb2xlIjoiQURNSU4iLCJmaXJzdE5hbWUiOiJTYW11ZWwiLCJsYXN0TmFtZSI6IkJ1dGxlciIsInBhc3N3b3JkUmVzZXQiOmZhbHNlLCJlbnYiOiJMT0NBTCIsImV4cCI6MTY2MjI3MjE2NSwidXNlcklkIjoxLCJpYXQiOjE2NjIyNTQxNjUsImVtYWlsIjoic2FtYnV0bGVyMTAxN0BpY2xvdWQuY29tIiwiand0VHlwZSI6IldFQiJ9.peZm8Kn3G__gEAy6X0sgTtGiPXOc8OUD2ksB0jYu4bu-JDVpdTawncbRGKjZaTFAx0r6iCzDLReeoCQOQVo3Qg";

    public static void main(String[] args) {
        start(args);
        runForever();
    }

    /**
     * Method for showing methods that need to start as soon as the application
     * begings.
     * 
     * @param args The arguements to evaluate.
     */
    private static void start(String[] args) {
        PiBridgeEnvironmentService.setActiveEnvironment(args);
        LoggerConfig.init();
        startSubscription();
    }

    /**
     * Abstract method to trigger a run forever application.
     */
    private static void runForever() {
        while (true) {
        }
    }

    private static void startSubscription() {
        SubscriptionClient client = new SubscriptionClient();
        client.connectAsync(url).subscribe(res -> addListeners(client));
    }

    private static void addListeners(SubscriptionClient client) {
        SubscriptionListeners listeners = new SubscriptionListeners(client);
        listeners.register(new GeneralNotficationListener());
    }
}
