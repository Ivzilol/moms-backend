package bg.mck.userqueryservice.application.events;



import bg.mck.userqueryservice.application.enums.EventType;


import java.time.LocalDateTime;


public abstract class BaseEvent {

    private Long userId;
    private EventType eventType;
    private LocalDateTime localDateTime;

    public BaseEvent() {
    }

    public BaseEvent(EventType eventType, Long userId, LocalDateTime localDateTime ) {
        this.eventType = eventType;
        this.userId = userId;
        this.localDateTime = localDateTime;
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
