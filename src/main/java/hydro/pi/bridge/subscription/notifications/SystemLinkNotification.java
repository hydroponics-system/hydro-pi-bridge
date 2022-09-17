package hydro.pi.bridge.subscription.notifications;

import hydro.pi.bridge.subscription.domain.Notification;
import hydro.pi.bridge.subscription.domain.NotificationType;

/**
 * Link Notification object for a system.
 * 
 * @author Sam Butler
 * @since September 16, 2022
 */
public class SystemLinkNotification extends Notification {
    private String uuid;

    private String code;

    private int userId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public NotificationType getBodyType() {
        return NotificationType.SYSTEM_LINK;
    }
}
