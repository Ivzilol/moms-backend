package bg.mck.userqueryservice.application.events;


import bg.mck.userqueryservice.application.enums.EventType;

import java.time.LocalDateTime;
import java.util.Objects;
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

    public RegisteredUserEvent(EventType eventType, Long userId, LocalDateTime localDateTime, String email, String password,
                               String firstName, String lastName, String phoneNumber,
                               boolean isActive, Set<String> roles) {
        super(eventType, userId, localDateTime);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredUserEvent that = (RegisteredUserEvent) o;
        return isActive == that.isActive && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, firstName, lastName, phoneNumber, isActive, roles);
    }
}
