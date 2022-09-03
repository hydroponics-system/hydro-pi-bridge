package hydro.pi.bridge.susbcription.domain;

/**
 * NotificationBodyType contains all known {@link Notification} declerations.
 *
 * @author Sam Butler
 * @since March 24, 2022
 */
public enum NotificationType {
    USER("USER"),
    SYSTEM_FAILURE("SYSTEM_FAILURE");

    private String textId;

    private NotificationType(String textId) {
        this.textId = textId;
    }

    public String getTextId() {
        return textId;
    }
}
