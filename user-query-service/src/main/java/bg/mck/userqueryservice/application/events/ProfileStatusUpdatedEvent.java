package bg.mck.userqueryservice.application.events;


import bg.mck.userqueryservice.application.enums.EventType;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProfileStatusUpdatedEvent extends BaseEvent {

    private boolean isActive;

    public ProfileStatusUpdatedEvent() {
    }

    public ProfileStatusUpdatedEvent(EventType eventType, Long userId, LocalDateTime localDateTime, boolean isActive) {
        super(eventType, userId, localDateTime);
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public ProfileStatusUpdatedEvent setActive(boolean active) {
        isActive = active;
        return this;
    }


}
