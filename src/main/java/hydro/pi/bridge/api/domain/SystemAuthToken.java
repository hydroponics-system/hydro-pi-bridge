package hydro.pi.bridge.api.domain;

import java.time.LocalDateTime;

import com.hydro.common.dictionary.data.HydroSystem;

/**
 * Authentication token for a system.
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class SystemAuthToken {

    private String token;

    private LocalDateTime createDate;

    private LocalDateTime expireDate;

    private HydroSystem body;

    public SystemAuthToken() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public HydroSystem getBody() {
        return body;
    }

    public void setBody(HydroSystem body) {
        this.body = body;
    }
}
