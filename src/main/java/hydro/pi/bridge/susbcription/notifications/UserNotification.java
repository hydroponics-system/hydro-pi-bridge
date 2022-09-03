package hydro.pi.bridge.susbcription.notifications;

import hydro.pi.bridge.susbcription.domain.Notification;
import hydro.pi.bridge.susbcription.domain.NotificationType;

/**
 * User Subscription Notification
 * 
 * @author Sam Butler
 * @since March 24, 2022
 */
public class UserNotification implements Notification {
    private int userId;

    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.USER;
    }
}