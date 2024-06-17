package bg.mck.usercommandservice.application.events;

import bg.mck.usercommandservice.application.enums.EventType;

public class UserEvent<T extends BaseEvent> {

    private EventType eventType;
    private T event;

    public UserEvent() {
    }

    public UserEvent(EventType eventType, T event) {
        this.eventType = eventType;
        this.event = event;
    }

    public EventType getEventType() {
        return eventType;
    }

    public UserEvent<T> setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public T getEvent() {
        return event;
    }

    public UserEvent<T> setEvent(T event) {
        this.event = event;
        return this;
    }
}
