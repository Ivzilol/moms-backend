package bg.mck.usercommandservice.application.dto;

import bg.mck.usercommandservice.application.validation.annotation.FieldMatcher;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@FieldMatcher(first = "newPassword",second = "confirmPassword")
public class UserChangePasswordDTO {

    @NotEmpty(message = "Current password cannot be empty!")
    private String currentPassword;
    @NotEmpty(message = "New password cannot be empty!")
    @Size(min = 6, message = "New password must contain a minimum of 6 characters")
    private String newPassword;
    @NotEmpty(message = "Confirm password cannot be empty!")
    @Size(min = 6, message = "Confirm password must contain a minimum of 6 characters")
    private String confirmPassword;

    public UserChangePasswordDTO() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public UserChangePasswordDTO setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UserChangePasswordDTO setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserChangePasswordDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
