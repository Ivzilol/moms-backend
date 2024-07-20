package bg.mck.events.transport;

import bg.mck.enums.EventType;

import java.time.LocalDateTime;

public abstract class BaseTransportEvent {

    private Long transportId;

    private EventType eventType;

    private LocalDateTime localDateTime;

    public BaseTransportEvent() {
    }

    public BaseTransportEvent(Long transportId, EventType eventType) {
        this.transportId = transportId;
        this.eventType = eventType;
        this.localDateTime = LocalDateTime.now();
    }

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
