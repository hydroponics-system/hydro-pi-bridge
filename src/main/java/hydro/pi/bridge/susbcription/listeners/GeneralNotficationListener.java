package hydro.pi.bridge.susbcription.listeners;

import hydro.pi.bridge.susbcription.notifications.UserNotification;

/**
 * General notification listener that gets triggered on base notifications.
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class GeneralNotficationListener implements Listener<UserNotification> {
    private static final String SOCKET_PATH = "/topic/general/notification";

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
        return SOCKET_PATH;
    }
}
