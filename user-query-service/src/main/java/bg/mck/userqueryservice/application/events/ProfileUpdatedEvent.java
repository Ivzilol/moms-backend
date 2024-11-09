package bg.mck.userqueryservice.application.events;


import bg.mck.userqueryservice.application.enums.EventType;

import java.time.LocalDateTime;
import java.util.Set;

public class ProfileUpdatedEvent extends BaseEvent {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<String> roles;

    public ProfileUpdatedEvent() {
    }

    public ProfileUpdatedEvent(EventType eventType, Long userId, LocalDateTime localDateTime, String email, String firstName, String lastName, String phoneNumber, Set<String> roles) {
        super(eventType, userId, localDateTime);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
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

    public Set<String> getRoles() {
        return roles;
    }

    public ProfileUpdatedEvent setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileUpdatedEvent setEmail(String email) {
        this.email = email;
        return this;
    }

}
