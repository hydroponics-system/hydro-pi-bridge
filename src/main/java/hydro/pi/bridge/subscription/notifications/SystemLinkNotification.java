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

    @Override
    public NotificationType getBodyType() {
        return NotificationType.SYSTEM_LINK;
    }
}
