package bg.mck.userqueryservice.application.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class UserDetailsDTO {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @JsonProperty("isActive")
    private boolean isActive;
    private Set<String> roles;

    public String getId() {
        return id;
    }

    public UserDetailsDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetailsDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDetailsDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserDetailsDTO setActive(boolean active) {
        isActive = active;
        return this;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public UserDetailsDTO setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }
}
