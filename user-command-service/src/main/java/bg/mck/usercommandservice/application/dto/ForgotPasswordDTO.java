package bg.mck.usercommandservice.application.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ForgotPasswordDTO {

    @NotNull
    @NotEmpty
    @Email(message = "Invalid email")
    private String email;

    public ForgotPasswordDTO(String email) {
        this.email = email;
    }

    public ForgotPasswordDTO() {
    }

    public String getEmail() {
        return email;
    }

    public ForgotPasswordDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
