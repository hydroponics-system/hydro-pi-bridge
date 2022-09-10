package hydro.pi.bridge.system.auth;

import com.hydro.common.dictionary.data.HydroSystem;

import hydro.pi.bridge.api.domain.SystemAuthToken;

/**
 * System Pair object for storing the token and current system.
 * 
 * @author Sam Butler
 * @since September 7, 2022
 */
public class SystemPair {
    private String token;

    private HydroSystem system;

    public SystemPair() {}

    public SystemPair(SystemAuthToken auth) {
        this.token = auth.getToken();
        this.system = auth.getBody();
    }

    public String getToken() {
        return token;
    }

    public HydroSystem getSystem() {
        return system;
    }

    /**
     * Clears the system pair of the current data.
     */
    public void remove() {
        this.token = null;
        this.system = null;
    }
}
