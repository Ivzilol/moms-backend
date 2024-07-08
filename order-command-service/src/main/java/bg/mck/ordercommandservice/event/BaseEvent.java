package bg.mck.ordercommandservice.event;

import java.time.LocalDateTime;

public abstract class BaseEvent {

    private Long OrderId;

    private OrderEventType eventType;

    private LocalDateTime localDateTime;

    public BaseEvent() {
    }

    public BaseEvent(Long orderId, OrderEventType eventType, LocalDateTime localDateTime) {
        OrderId = orderId;
        this.eventType = eventType;
        this.localDateTime = localDateTime;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public BaseEvent setOrderId(Long orderId) {
        OrderId = orderId;
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
