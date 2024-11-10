package bg.mck.usercommandservice.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class UserCredentialsDTO {

    @NotBlank(message = "Password must not be blank")
    private String hashedPassword;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be blank")
    private String email;


    public @Email(message = "Invalid email format") @NotBlank(message = "Email must not be blank") String getEmail() {
        return email;
    }

    public UserCredentialsDTO setEmail(@Email(message = "Invalid email format") @NotBlank(message = "Email must not be blank") String email) {
        this.email = email;
        return this;
    }

    public @NotBlank(message = "Password must not be blank") String getHashedPassword() {
        return hashedPassword;
    }

    public UserCredentialsDTO setHashedPassword(@NotBlank(message = "Password must not be blank") String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return this;
    }
}
