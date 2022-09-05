package hydro.pi.bridge.system.service;

import java.util.HashMap;

import hydro.pi.bridge.api.ApiClient;
import hydro.pi.bridge.api.domain.AuthenticationRequest;
import hydro.pi.bridge.api.domain.SystemAuthToken;
import hydro.pi.bridge.api.domain.UserAuthToken;
import hydro.pi.bridge.common.file.FileReader;

/**
 * System Authentication class for managing a systems authentication staus for
 * the sytem.
 * 
 * @author Sam Butler
 * @since September 5, 2022
 */
public class SystemAuthenticationService {
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    public SystemAuthToken systemAuthentication() {
        return null;
    }

    /**
     * Simple user authentication on the system. Used when creating or resetting a
     * new system.
     * 
     * @return The {@link UserAuthToken} of the use.
     * @throws Exception If the user can not be authenticated.
     */
    public UserAuthToken userAuthentication() throws Exception {
        ApiClient client = new ApiClient();
        return client.post("/api/authenticate", buildUserAuthRequest(), UserAuthToken.class);
    }

    /**
     * Method for building a user request object based on the set local settings.
     * 
     * @return The authentication request object.
     */
    private AuthenticationRequest buildUserAuthRequest() {
        HashMap<String, String> localMap = FileReader.readLocalEnvironment();
        return new AuthenticationRequest(localMap.get(USERNAME_KEY), localMap.get(PASSWORD_KEY));
    }
}
