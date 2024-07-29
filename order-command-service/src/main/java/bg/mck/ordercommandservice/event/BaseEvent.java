package bg.mck.ordercommandservice.event;

import java.time.LocalDateTime;

public abstract class BaseEvent {

    private Long orderId;
    private EventType eventType;
    private LocalDateTime eventTime;

    public BaseEvent() {
    }

    public BaseEvent(Long orderId, EventType eventType, LocalDateTime eventTime) {
        this.orderId = orderId;
        this.eventType = eventType;
        this.eventTime = eventTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public BaseEvent setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
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
