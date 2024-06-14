package bg.mck.authenticationservice.model;

import java.util.Set;

public class User {

    private String id;
    private String email;
    private Set<String> authorities;

    public User(String id, String email, Set<String> authorities) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public User setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
        return this;
    }
}
