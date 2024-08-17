package bg.mck.ordercommandservice.event;

import java.time.LocalDateTime;

public abstract class BaseEvent {

    private EventType eventType;
    private LocalDateTime eventTime;

    public BaseEvent() {
    }

    public BaseEvent(EventType eventType, LocalDateTime eventTime) {
        this.eventType = eventType;
        this.eventTime = eventTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public BaseEvent setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public BaseEvent setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
        return this;
    }
}
