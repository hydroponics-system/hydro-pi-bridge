package hydro.pi.bridge.api.service;

import hydro.pi.bridge.api.ApiClient;
import hydro.pi.bridge.api.domain.AuthenticationRequest;
import hydro.pi.bridge.api.domain.SystemAuthToken;
import hydro.pi.bridge.api.domain.UserAuthToken;

/**
 * System Authentication class for managing a systems authentication staus for
 * the sytem.
 * 
 * @author Sam Butler
 * @since September 5, 2022
 */
public class SystemAuthenticationManager {

    public SystemAuthToken systemAuthentication() {
        return null;
    }

    public UserAuthToken userAuthentication() throws Exception {
        ApiClient client = new ApiClient();

        return client.post("/api/authenticate",
                           new AuthenticationRequest("sambutler1017@icloud.com", "78e05bf74fad284798a195ec2ff3ae6D!"),
                           UserAuthToken.class);
    }
}
