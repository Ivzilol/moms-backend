package bg.mck.usercommandservice.application.dto;

import bg.mck.usercommandservice.application.validation.annotation.FieldMatcher;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@FieldMatcher(
        first = "password",
        second = "confirmPassword"
)
public class ResetPasswordDTO {

    @NotEmpty
    private String token;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "The password must contain a minimum of 6 characters")
    private String password;

    @NotEmpty(message = "Confirm password cannot be empty")
    @Size(min = 6, message = "The password must contain a minimum of 6 characters")
    private String confirmPassword;

    public ResetPasswordDTO(String token, String password, String confirmPassword) {
        this.token = token;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public @NotEmpty String getToken() {
        return token;
    }

    public ResetPasswordDTO setToken(@NotEmpty String token) {
        this.token = token;
        return this;
    }

    public @NotEmpty(message = "Password cannot be empty") @Size(min = 6, message = "The password must contain a minimum of 6 characters") String getPassword() {
        return password;
    }

    public ResetPasswordDTO setPassword(@NotEmpty(message = "Password cannot be empty") @Size(min = 6, message = "The password must contain a minimum of 6 characters") String password) {
        this.password = password;
        return this;
    }

    public @NotEmpty(message = "Confirm password cannot be empty") @Size(min = 6, message = "The password must contain a minimum of 6 characters") String getConfirmPassword() {
        return confirmPassword;
    }

    public ResetPasswordDTO setConfirmPassword(@NotEmpty(message = "Confirm password cannot be empty") @Size(min = 6, message = "The password must contain a minimum of 6 characters") String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
