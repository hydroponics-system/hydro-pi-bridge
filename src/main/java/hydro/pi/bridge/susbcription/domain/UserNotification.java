package hydro.pi.bridge.susbcription.domain;

/**
 * User Subscription Notification
 * 
 * @author Sam Butler
 * @since March 24, 2022
 */
public class UserNotification implements NotificationBody {
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
    public NotificationBodyType getBodyType() {
        return NotificationBodyType.USER;
    }
}