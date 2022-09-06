package hydro.pi.bridge.system.client.domain;

import java.io.Serializable;

import javax.annotation.Nonnull;

/**
 * AuthenticationRequest for authenticating and updating System credentials.
 *
 * @author Sam Butler
 * @since August 1, 20222
 */
public class SystemAuthenticationRequest implements Serializable {

    @Nonnull
    private String uuid;

    @Nonnull
    private String password;

    public SystemAuthenticationRequest() {}

    public SystemAuthenticationRequest(String uuid, String password) {
        this.uuid = uuid;
        this.password = password;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
