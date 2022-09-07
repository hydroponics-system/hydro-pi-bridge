package hydro.pi.bridge.system.service;

import java.util.HashMap;

import hydro.pi.bridge.api.ApiClient;
import hydro.pi.bridge.api.domain.SystemAuthToken;
import hydro.pi.bridge.api.domain.UserAuthToken;
import hydro.pi.bridge.common.file.FileReader;
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
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    private static final String SYSTEM_AUTH_API = "/api/system-app/authenticate";
    private static final String USER_AUTH_API = "/api/authenticate";

    private SystemJwtHolder systemJwtHolder;
    private ApiClient apiClient;

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
        return apiClient.post(USER_AUTH_API, buildUserAuthRequest(), UserAuthToken.class);
    }

    /**
     * Method for building a user request object based on the set local settings.
     * 
     * @return The authentication request object.
     */
    private UserAuthenticationRequest buildUserAuthRequest() {
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
        return new SystemAuthenticationRequest("5326c0e3-535c-3973-b4a3-8dd17da88a63", "password!s");
    }
}
