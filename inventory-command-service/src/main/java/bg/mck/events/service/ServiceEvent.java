package bg.mck.events.service;

import bg.mck.enums.EventType;


public class ServiceEvent<T extends BaseServiceEvent> {

    private EventType eventType;

    private T event;

    public ServiceEvent() {
    }

    public ServiceEvent(EventType eventType, T event) {
        this.eventType = eventType;
        this.event = event;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public T getEvent() {
        return event;
    }

    public void setEvent(T event) {
        this.event = event;
    }

}
