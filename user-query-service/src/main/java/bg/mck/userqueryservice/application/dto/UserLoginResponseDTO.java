package bg.mck.userqueryservice.application.dto;

import java.util.Set;

public class UserLoginResponseDTO {

    private String id;
    private String email;
    private Set<String> roles;
    private String token;

    public UserLoginResponseDTO(String id, String email, Set<String> authorities, String token) {
        this.id = id;
        this.email = email;
        this.roles = authorities;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public UserLoginResponseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserLoginResponseDTO setEmail(String email) {
        this.email = email;
        return this;
    }


    public Set<String> getRoles() {
        return roles;
    }

    public UserLoginResponseDTO setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UserLoginResponseDTO setToken(String token) {
        this.token = token;
        return this;
    }
}
