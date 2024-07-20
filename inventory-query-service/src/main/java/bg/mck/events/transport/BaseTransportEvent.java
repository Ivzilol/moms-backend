package bg.mck.events.transport;

import bg.mck.enums.EventType;

import java.time.LocalDateTime;

public abstract class BaseTransportEvent {

    private String transportId;

    private EventType eventType;

    private LocalDateTime localDateTime;

    public BaseTransportEvent() {
    }

    public BaseTransportEvent(String transportId, EventType eventType) {
        this.transportId = transportId;
        this.eventType = eventType;
        this.localDateTime = LocalDateTime.now();
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

    public String getTransportId() {
        return transportId;
    }

    public BaseTransportEvent setTransportId(String transportId) {
        this.transportId = transportId;
        return this;
    }
}
