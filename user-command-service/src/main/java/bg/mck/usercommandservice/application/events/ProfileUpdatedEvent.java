package bg.mck.usercommandservice.application.events;


import bg.mck.usercommandservice.application.enums.EventType;

import java.util.Set;

public class ProfileUpdatedEvent extends BaseEvent {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<String> roles;

    public ProfileUpdatedEvent() {
    }

    public ProfileUpdatedEvent(EventType eventType, Long userId, String email, String firstName, String lastName, String phoneNumber, Set<String> roles) {
        super(eventType, userId);
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

    public String getEmail() {
        return email;
    }

    public ProfileUpdatedEvent setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public ProfileUpdatedEvent setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }
}
