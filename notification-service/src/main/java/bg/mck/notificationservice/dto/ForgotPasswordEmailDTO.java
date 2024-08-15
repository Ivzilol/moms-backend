package bg.mck.notificationservice.dto;

public class ForgotPasswordEmailDTO {

    private String email;

    private String uuid;

    public ForgotPasswordEmailDTO(String email, String uuid) {
        this.email = email;
        this.uuid = uuid;
    }

    public ForgotPasswordEmailDTO() {
    }

    public String getEmail() {
        return email;
    }

    public ForgotPasswordEmailDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public ForgotPasswordEmailDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
}

