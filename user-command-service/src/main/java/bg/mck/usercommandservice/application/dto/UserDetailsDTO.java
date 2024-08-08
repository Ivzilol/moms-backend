package bg.mck.usercommandservice.application.dto;


import bg.mck.usercommandservice.application.validation.annotation.UniqueEmail;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDetailsDTO {

    @UniqueEmail
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "First Name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Phone Number cannot be empty")
    private String phoneNumber;


    public @NotEmpty(message = "First Name cannot be empty") String getFirstName() {
        return firstName;
    }

    public UserDetailsDTO setFirstName(@NotEmpty(message = "First Name cannot be empty") String firstName) {
        this.firstName = firstName;
        return this;
    }

    public @NotEmpty(message = "Last Name cannot be empty") String getLastName() {
        return lastName;
    }

    public UserDetailsDTO setLastName(@NotEmpty(message = "Last Name cannot be empty") String lastName) {
        this.lastName = lastName;
        return this;
    }

    public @NotEmpty(message = "Phone Number cannot be empty") String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDetailsDTO setPhoneNumber(@NotEmpty(message = "Phone Number cannot be empty") String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public @Email(message = "Email should be valid") @NotEmpty(message = "Email cannot be empty") String getEmail() {
        return email;
    }

    public UserDetailsDTO setEmail(@Email(message = "Email should be valid") @NotEmpty(message = "Email cannot be empty") String email) {
        this.email = email;
        return this;
    }
}
