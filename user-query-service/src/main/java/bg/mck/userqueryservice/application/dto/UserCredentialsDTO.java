package bg.mck.userqueryservice.application.dto;


public class UserCredentialsDTO {

    private String hashedPassword;
    private String email;

    public  String getHashedPassword() {
        return hashedPassword;
    }

    public UserCredentialsDTO setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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
