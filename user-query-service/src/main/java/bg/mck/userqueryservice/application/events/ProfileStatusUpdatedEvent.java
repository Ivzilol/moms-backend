package bg.mck.userqueryservice.application.events;


public class ProfileStatusUpdatedEvent extends BaseEvent {

    private boolean isActive;

    public ProfileStatusUpdatedEvent() {
    }

    public boolean isActive() {
        return isActive;
    }

    public ProfileStatusUpdatedEvent setActive(boolean active) {
        isActive = active;
        return this;
    }

}
