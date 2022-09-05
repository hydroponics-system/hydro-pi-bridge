package hydro.pi.bridge.api.domain;

import java.time.LocalDateTime;

import com.hydro.common.dictionary.data.User;

/**
 * Authentication token for a user
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class UserAuthToken {

    private String token;

    private LocalDateTime createDate;

    private LocalDateTime expireDate;

    private User body;

    public UserAuthToken() {}

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

    public User getBody() {
        return body;
    }

    public void setBody(User body) {
        this.body = body;
    }
}
