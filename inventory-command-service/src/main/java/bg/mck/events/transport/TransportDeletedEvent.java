package bg.mck.events.transport;

import bg.mck.enums.EventType;

public class TransportDeletedEvent extends BaseTransportEvent {

    private String name;

    public TransportDeletedEvent() {
    }

    public TransportDeletedEvent(Long transportId, EventType eventType, String name) {
        super(transportId, eventType);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TransportDeletedEvent setName(String name) {
        this.name = name;
        return this;
    }
}
