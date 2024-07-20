package bg.mck.events.service;

import bg.mck.enums.EventType;

public class ServiceDeletedEvent extends BaseServiceEvent{

    private String name;

    public ServiceDeletedEvent() {
    }

    public ServiceDeletedEvent(Long serviceId, EventType eventType, String name) {
        super(serviceId, eventType);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ServiceDeletedEvent setName(String name) {
        this.name = name;
        return this;
    }
}
