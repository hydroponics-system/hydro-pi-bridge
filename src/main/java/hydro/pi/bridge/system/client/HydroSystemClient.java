package hydro.pi.bridge.system.client;

import hydro.pi.bridge.api.domain.SystemAuthToken;
import hydro.pi.bridge.api.domain.UserAuthToken;
import hydro.pi.bridge.environment.PiBridgeEnvironmentService;
import hydro.pi.bridge.susbcription.client.SubscriptionClient;
import hydro.pi.bridge.susbcription.listeners.GeneralNotficationListener;
import hydro.pi.bridge.susbcription.service.SubscriptionListeners;
import hydro.pi.bridge.system.service.SystemAuthenticationService;

/**
 * Hydro System client for dealing with the users hydroponic system.
 * 
 * @author Sam Butler
 * @since September 5, 2022
 */
public class HydroSystemClient {
    private SubscriptionClient subscriptionClient;

    private SystemAuthenticationService systemAuthenticationService;

    /**
     * Default constructor for setting up the instances of the system and
     * subscription client.
     */
    public HydroSystemClient() {
        this.subscriptionClient = new SubscriptionClient();
        this.systemAuthenticationService = new SystemAuthenticationService();
    }

    /**
     * Start the process for the hydroponic System. This will register a system if
     * it is new or authenticate if the system is already configured. It will also
     * add the defined listeners for the system subscription.
     * @throws Exception If the user can not be authenticated.
     */
    public void start() throws Exception {
        SystemAuthToken systemAuth = systemAuthenticationService.systemAuthentication();
        startSystemSubscription(buildSocketUrl(systemAuth.getToken()));
    }

    /**
     * If the system is not registered yet this method will be used to set that up.
     * It will use the users credentials to register them system with the API.
     */
    public void registerSystem() {
        // TODO: Add Funtionality to register a system.
    }

    /**
     * Builds the socket connection string from the provided token. This token can
     * be either a {@link SystemAuthToken} or {@link UserAuthToken}.
     * 
     * @param systemToken The system token to be authenticated.
     * @return String of the socket url to connect with.
     */
    private String buildSocketUrl(String systemToken) {
        return String.format("%s?%s", PiBridgeEnvironmentService.active().socket(), systemToken);
    }

    /**
     * Starts an active hydro system connection with the API client.
     * 
     * @param socketUrl The socket path to connect too.
     */
    private void startSystemSubscription(String socketUrl) {
        subscriptionClient.connectAsync(socketUrl).subscribe(res -> addSystemListeners(subscriptionClient));
    }

    /**
     * Adds system specific listners for the system so a user can manage the system
     * remotely and through the application if needed.
     * 
     * @param client The active system client subscription.
     */
    private void addSystemListeners(SubscriptionClient client) {
        SubscriptionListeners listeners = new SubscriptionListeners(client);
        listeners.register(new GeneralNotficationListener());
    }
}