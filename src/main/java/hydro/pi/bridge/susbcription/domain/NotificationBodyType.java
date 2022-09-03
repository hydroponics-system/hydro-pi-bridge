package hydro.pi.bridge.susbcription.domain;

/**
 * NotificationBodyType contains all known {@link NotificationBody}
 * declerations.
 *
 * @author Sam Butler
 * @since March 24, 2022
 */
public enum NotificationBodyType {
    USER("USER"),
    SYSTEM_FAILURE("SYSTEM_FAILURE");

    private String textId;

    private NotificationBodyType(String textId) {
        this.textId = textId;
    }

    public String getTextId() {
        return textId;
    }
}
