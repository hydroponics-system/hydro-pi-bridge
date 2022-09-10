package hydro.pi.bridge.subscription.listeners;

import hydro.pi.bridge.subscription.notifications.UserNotification;
import hydro.pi.bridge.system.auth.SystemJwtHolder;

/**
 * General notification listener that gets triggered on base notifications.
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class SystemNotficationListener implements Listener<UserNotification> {
    private static final String SOCKET_PATH = "/queue/system/notification-%s";

    private final SystemJwtHolder systemJwtHolder;

    public SystemNotficationListener() {
        systemJwtHolder = new SystemJwtHolder();
    }

    @Override
    public void run(UserNotification result) {
        System.out.println("User ID: " + result.getUserId());
        System.out.println("Name: " + result.getName());
    }

    @Override
    public Class<UserNotification> type() {
        return UserNotification.class;
    }

    @Override
    public String url() {
        return String.format(SOCKET_PATH, systemJwtHolder.getSystem().getUuid());
    }
}
