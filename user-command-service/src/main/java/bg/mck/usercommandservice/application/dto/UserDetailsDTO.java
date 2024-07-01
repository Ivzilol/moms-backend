package bg.mck.usercommandservice.application.dto;


import jakarta.validation.constraints.NotEmpty;

public class UserDetailsDTO {

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "First Name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Phone Number cannot be empty")
    private String phoneNumber;


    public @NotEmpty(message = "Password cannot be empty") String getPassword() {
        return password;
    }

    public UserDetailsDTO setPassword(@NotEmpty(message = "Password cannot be empty") String password) {
        this.password = password;
        return this;
    }

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
}
