package hydro.pi.bridge.system.service;

import java.util.HashMap;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hydro.pi.bridge.api.ApiClient;
import hydro.pi.bridge.api.domain.SystemAuthToken;
import hydro.pi.bridge.api.domain.UserAuthToken;
import hydro.pi.bridge.common.file.FileReader;
import hydro.pi.bridge.environment.PiBridgeEnvironmentService;
import hydro.pi.bridge.environment.PiEnvironment;
import hydro.pi.bridge.system.auth.SystemJwtHolder;
import hydro.pi.bridge.system.client.domain.SystemAuthenticationRequest;
import hydro.pi.bridge.system.client.domain.UserAuthenticationRequest;

/**
 * System Authentication class for managing a systems authentication status for
 * the sytem.
 * 
 * @author Sam Butler
 * @since September 5, 2022
 */
public class SystemAuthenticationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemAuthenticationService.class);

    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String SYSTEM_AUTH_API = "/api/system-app/authenticate";
    private static final String USER_AUTH_API = "/api/authenticate";

    private final SystemJwtHolder systemJwtHolder;

    private final ApiClient apiClient;

    /**
     * Default constructor for the authentication service tha will intialize the
     * {@link ApiClient} and the {@link SystemJwtHolder}.
     */
    public SystemAuthenticationService() {
        this.apiClient = new ApiClient();
        this.systemJwtHolder = new SystemJwtHolder();
    }

    /**
     * Will authenticate the system. If a successful request was returned then it
     * will set the thread local instance to the returned data and token.
     * 
     * @throws Exception If the request can not be completed.
     */
    public void authenticateSystem() throws Exception {
        SystemAuthToken auth = apiClient.post(SYSTEM_AUTH_API, buildSystemAuthRequest(), SystemAuthToken.class);
        systemJwtHolder.set(auth);
    }

    /**
     * Simple user authentication on the system. Used when creating or resetting a
     * new system.
     * 
     * @return The {@link UserAuthToken} of the use.
     * @throws Exception If the user can not be authenticated.
     */
    public UserAuthToken authenticateUser() throws Exception {
        if(PiBridgeEnvironmentService.active().equals(PiEnvironment.PRODUCTION)) {
            LOGGER.info("System needs authentication. Please enter user Credentials.");
            return apiClient.post(USER_AUTH_API, requestUserCredentials(), UserAuthToken.class);
        }
        else {
            return apiClient.post(USER_AUTH_API, buildLocalUserAuthRequest(), UserAuthToken.class);
        }
    }

    /**
     * Request the users credentials from the terminal. This is only called when the
     * system is not authenticated or not a local development.
     * 
     * @return The user authentication request.
     */
    private UserAuthenticationRequest requestUserCredentials() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();
        scanner.close();
        return new UserAuthenticationRequest(username, password);
    }

    /**
     * Method for building a user request object based on the set local settings.
     * 
     * @return The authentication request object.
     */
    private UserAuthenticationRequest buildLocalUserAuthRequest() {
        HashMap<String, String> localMap = FileReader.readLocalEnvironment();
        return new UserAuthenticationRequest(localMap.get(USERNAME_KEY), localMap.get(PASSWORD_KEY));
    }

    /**
     * Helper method for building a system authentication request.
     * 
     * TODO: Update to pull UUID and password for locally encrypted file.
     * 
     * @return System Authentication Request object with the uuid and password.
     */
    private SystemAuthenticationRequest buildSystemAuthRequest() {
        return new SystemAuthenticationRequest("5326c0e3-535c-3973-b4a3-8dd17da88a63", "password!");
    }
}
