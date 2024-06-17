package bg.mck.usercommandservice.application.events;

import bg.mck.usercommandservice.application.enums.EventType;

import java.util.Set;

public class RegisteredUserEvent extends BaseEvent{

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isActive;
    private Set<String> roles;

    public RegisteredUserEvent() {
    }

    public RegisteredUserEvent(EventType eventType, Long userId, String email, String password,
                               String firstName, String lastName, String phoneNumber,
                               boolean isActive, Set<String> roles) {
        super(eventType, userId);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public RegisteredUserEvent setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisteredUserEvent setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public RegisteredUserEvent setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisteredUserEvent setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RegisteredUserEvent setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public RegisteredUserEvent setActive(boolean active) {
        isActive = active;
        return this;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public RegisteredUserEvent setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }
}
