package bg.mck.userqueryservice.application.dto;

import java.util.Set;

public class UserLoginResponseDTO {

    private String id;
    private String email;
    private Set<String> authorities;

    public UserLoginResponseDTO(String id, String email, Set<String> authorities) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
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


    public Set<String> getAuthorities() {
        return authorities;
    }

    public UserLoginResponseDTO setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
        return this;
    }
}
