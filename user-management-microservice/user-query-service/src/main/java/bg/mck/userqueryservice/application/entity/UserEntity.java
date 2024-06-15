package bg.mck.userqueryservice.application.entity;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "queryEntity")
public class UserEntity extends BaseEntity {


    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<String> authorities;


    public UserEntity(String email, String password, String firstName, String lastName, String phoneNumber, Set<String> authorities) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.authorities = authorities;
    }


    public UserEntity() {
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public UserEntity setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
        return this;
    }
}
