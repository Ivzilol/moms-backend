package bg.mck.usercommandservice.application.events;

import bg.mck.usercommandservice.application.enums.EventType;

import java.time.LocalDateTime;

public class BaseEvent {

    private EventType eventType;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public BaseEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public BaseEvent setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }
}
