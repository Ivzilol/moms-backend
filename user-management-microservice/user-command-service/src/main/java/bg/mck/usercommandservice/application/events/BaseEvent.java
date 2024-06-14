package bg.mck.usercommandservice.application.events;

import bg.mck.usercommandservice.application.enums.EvenType;

import java.time.LocalDateTime;

public class BaseEvent {

    private EvenType eventType;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public BaseEvent(EvenType eventType) {
        this.eventType = eventType;
    }

    public EvenType getEventType() {
        return eventType;
    }

    public BaseEvent setEventType(EvenType eventType) {
        this.eventType = eventType;
        return this;
    }
}
