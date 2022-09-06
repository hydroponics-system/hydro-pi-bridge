package hydro.pi.bridge.system.service;

import java.util.HashMap;

import hydro.pi.bridge.api.ApiClient;
import hydro.pi.bridge.api.domain.SystemAuthToken;
import hydro.pi.bridge.api.domain.UserAuthToken;
import hydro.pi.bridge.common.file.FileReader;
import hydro.pi.bridge.system.client.domain.SystemAuthenticationRequest;
import hydro.pi.bridge.system.client.domain.UserAuthenticationRequest;

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

    public SystemAuthToken systemAuthentication() throws Exception {
        ApiClient client = new ApiClient();
        return client.post("/api/system-app/authenticate", buildSystemAuthRequest(), SystemAuthToken.class);
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
        return new SystemAuthenticationRequest("5326c0e3-535c-3973-b4a3-8dd17da88a63", "password!");
    }
}
