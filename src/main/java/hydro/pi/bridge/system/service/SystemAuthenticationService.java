package hydro.pi.bridge.system.service;

import com.hydro.common.dictionary.data.HydroSystem;

import hydro.pi.bridge.api.ApiClient;
import hydro.pi.bridge.api.domain.SystemAuthToken;
import hydro.pi.bridge.system.auth.SystemJwtHolder;
import hydro.pi.bridge.system.client.domain.SystemAuthenticationRequest;

/**
 * System Authentication class for managing a systems authentication status for
 * the sytem.
 * 
 * @author Sam Butler
 * @since September 5, 2022
 */
public class SystemAuthenticationService {
    private static final String SYSTEM_AUTH_API = "/api/system-app/authenticate";

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
    public void authenticateSystem(HydroSystem system) throws Exception {
        SystemAuthToken auth = apiClient.post(SYSTEM_AUTH_API, buildSystemAuthRequest(system), SystemAuthToken.class);
        systemJwtHolder.set(auth);
    }

    /**
     * Helper method for building a system authentication request.
     * 
     * TODO: Update to pull UUID and password for locally encrypted file.
     * 
     * @return System Authentication Request object with the uuid and password.
     */
    private SystemAuthenticationRequest buildSystemAuthRequest(HydroSystem sys) {
        return new SystemAuthenticationRequest(sys.getUuid(), sys.getPassword());
    }
}
