package bg.mck.usercommandservice.application.events;

import bg.mck.usercommandservice.application.enums.EventType;


import java.time.LocalDateTime;


public abstract class BaseEvent {

    private Long userId;
    private EventType eventType;
    private LocalDateTime localDateTime;

    public BaseEvent() {
    }

    public BaseEvent(EventType eventType, Long userId) {
        this.eventType = eventType;
        this.userId = userId;
        this.localDateTime = LocalDateTime.now();
    }

    public EventType getEventType() {
        return eventType;
    }

    public BaseEvent setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public BaseEvent setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public BaseEvent setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
