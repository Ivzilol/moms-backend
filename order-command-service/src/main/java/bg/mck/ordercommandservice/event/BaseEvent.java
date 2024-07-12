package bg.mck.ordercommandservice.event;

import java.time.LocalDateTime;

public abstract class BaseEvent {

    private Long orderId;

    private OrderEventType eventType;

    private LocalDateTime localDateTime;

    public BaseEvent() {
    }

    public BaseEvent(Long orderId, OrderEventType eventType, LocalDateTime localDateTime) {
        this.orderId = orderId;
        this.eventType = eventType;
        this.localDateTime = localDateTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public BaseEvent setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderEventType getEventType() {
        return eventType;
    }

    public BaseEvent setEventType(OrderEventType eventType) {
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
}
