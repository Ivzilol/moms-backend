package bg.mck.usercommandservice.application.events;


import bg.mck.usercommandservice.application.enums.EventType;

public class PasswordUpdateEvent extends BaseEvent{

    private String newPassword;

    public PasswordUpdateEvent() {
    }

    public PasswordUpdateEvent(EventType eventType, Long userId, String newPassword) {
        super(eventType, userId);
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public PasswordUpdateEvent setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }
}
