package bg.mck.usercommandservice.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "forgot_password")
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    @NotNull
    private String uuid;

    public ForgotPassword(Long id, String userEmail, String uuid) {
        this.id = id;
        this.userEmail = userEmail;
        this.uuid = uuid;
    }

    public ForgotPassword() {
    }

    public Long getId() {
        return id;
    }

    public ForgotPassword setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public ForgotPassword setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public ForgotPassword setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
}
