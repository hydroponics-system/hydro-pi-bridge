package hydro.pi.bridge.subscription.domain;

/**
 * NotificationBodyType contains all known {@link NotificationBodyType}
 * declerations.
 *
 * @author Sam Butler
 * @since March 24, 2022
 */
public enum NotificationType {
    USER("USER"),
    SYSTEM_FAILURE("SYSTEM_FAILURE"),
    SYSTEM_LINK("SYSTEM_LINK");

    private String textId;

    private NotificationType(String textId) {
        this.textId = textId;
    }

    public String getTextId() {
        return textId;
    }
}
