package bg.mck.events.service;

import bg.mck.enums.EventType;

import java.time.LocalDateTime;
public abstract class BaseServiceEvent {

    private String serviceId;

    private EventType eventType;

    private LocalDateTime localDateTime;

    public BaseServiceEvent() {
    }

    public BaseServiceEvent(String serviceId, EventType eventType) {
        this.serviceId = serviceId;
        this.eventType = eventType;
        this.localDateTime = LocalDateTime.now();
    }

    public String getServiceId() {
        return serviceId;
    }

    public BaseServiceEvent setServiceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public EventType getEventType() {
        return eventType;
    }

    public BaseServiceEvent setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public BaseServiceEvent setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }
}
