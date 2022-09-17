package hydro.pi.bridge.subscription.listeners;

import hydro.pi.bridge.subscription.notifications.SystemLinkNotification;
import hydro.pi.bridge.system.auth.SystemJwtHolder;

/**
 * General notification listener that gets triggered on base notifications.
 * 
 * @author Sam Butler
 * @since September 2, 2022
 */
public class SystemLinkNotficationListener implements Listener<SystemLinkNotification> {
    private static final String SOCKET_PATH = "/queue/system/link/notification-%s";

    private final SystemJwtHolder systemJwtHolder;

    public SystemLinkNotficationListener() {
        systemJwtHolder = new SystemJwtHolder();
    }

    @Override
    public void run(SystemLinkNotification result) {
        System.out.println("System UUID: " + result.getUuid());
        System.out.println("Link Code: " + result.getCode());
    }

    @Override
    public Class<SystemLinkNotification> type() {
        return SystemLinkNotification.class;
    }

    @Override
    public String url() {
        return String.format(SOCKET_PATH, systemJwtHolder.getSystem().getUuid());
    }
}
