package hydro.pi.bridge.susbcription.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Added to entities to allow for notification type to be standardized when
 * publishing a message.
 * 
 * @author Sam Butler
 * @since March 24, 2022
 */
@JsonInclude(Include.NON_DEFAULT)
public interface NotificationBody {

    /**
     * A text value identifying the notification body type.
     * 
     * @return NotificationBodyType
     */
    NotificationBodyType getBodyType();
}
