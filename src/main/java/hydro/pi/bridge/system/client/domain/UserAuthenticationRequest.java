package hydro.pi.bridge.system.client.domain;

import java.io.Serializable;

import javax.annotation.Nonnull;

/**
 * AuthenticationRequest for authenticating and updating user credentials.
 *
 * @author Sam Butler
 * @since August 1, 2020
 */
public class UserAuthenticationRequest implements Serializable {

    @Nonnull
    private String email;

    @Nonnull
    private String password;

    public UserAuthenticationRequest() {}

    public UserAuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
