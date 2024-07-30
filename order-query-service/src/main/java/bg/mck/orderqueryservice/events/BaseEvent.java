package bg.mck.orderqueryservice.events;

import java.time.LocalDateTime;

public abstract class BaseEvent {

    private OrderEventType eventType;
    private LocalDateTime eventTime;

    public BaseEvent() {
    }

    public BaseEvent(OrderEventType eventType, LocalDateTime eventTime) {
        this.eventType = eventType;
        this.eventTime = eventTime;
    }

    public OrderEventType getEventType() {
        return eventType;
    }

    public BaseEvent setEventType(OrderEventType eventType) {
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
