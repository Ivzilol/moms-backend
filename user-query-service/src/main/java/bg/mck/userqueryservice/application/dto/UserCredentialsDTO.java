package bg.mck.userqueryservice.application.dto;


public class UserCredentialsDTO {

    private String email;

    public UserCredentialsDTO setHashedPassword(String hashedPassword) {
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserCredentialsDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
