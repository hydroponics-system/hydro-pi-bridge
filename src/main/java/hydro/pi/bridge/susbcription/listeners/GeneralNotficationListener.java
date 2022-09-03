package hydro.pi.bridge.susbcription.listeners;

import hydro.pi.bridge.susbcription.abstracts.Listener;
import hydro.pi.bridge.susbcription.domain.UserNotification;

/**
 * General notification listener that gets triggered on base notifications.
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class GeneralNotficationListener implements Listener<UserNotification> {
    private static final String SOCKET_PATH = "/topic/general/notification";

    public void run(UserNotification result) {
        System.out.println("User ID: " + result.getUserId());
        System.out.println("Name: " + result.getName());
    }

    public Class<UserNotification> resultType() {
        return UserNotification.class;
    }

    public String url() {
        return SOCKET_PATH;
    }
}
