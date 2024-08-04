package bg.mck.usercommandservice.application.events;


import bg.mck.usercommandservice.application.enums.EventType;


public class ProfileStatusUpdatedEvent extends BaseEvent {

    private boolean isActive;

    public ProfileStatusUpdatedEvent() {
    }

    public ProfileStatusUpdatedEvent(EventType eventType, Long userId, boolean isActive) {
        super(eventType, userId);
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public ProfileStatusUpdatedEvent setActive(boolean active) {
        isActive = active;
        return this;
    }
}
