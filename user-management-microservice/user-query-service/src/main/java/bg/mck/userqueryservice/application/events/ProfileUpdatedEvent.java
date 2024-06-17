package bg.mck.userqueryservice.application.events;


import bg.mck.userqueryservice.application.enums.EventType;

import java.time.LocalDateTime;

public class ProfileUpdatedEvent extends BaseEvent {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public ProfileUpdatedEvent() {
    }

    public ProfileUpdatedEvent(EventType eventType, Long userId, LocalDateTime localDateTime, String firstName, String lastName, String phoneNumber) {
        super(eventType, userId, localDateTime);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public ProfileUpdatedEvent setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileUpdatedEvent setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileUpdatedEvent setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

}
