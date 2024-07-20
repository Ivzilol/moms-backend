package bg.mck.events.service;

import bg.mck.enums.EventType;

import java.time.LocalDateTime;

public abstract class BaseServiceEvent {

    private Long serviceId;

    private EventType eventType;

    private LocalDateTime localDateTime;

    public BaseServiceEvent() {
    }

    public BaseServiceEvent(Long serviceId, EventType eventType) {
        this.serviceId = serviceId;
        this.eventType = eventType;
        this.localDateTime = LocalDateTime.now();
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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
