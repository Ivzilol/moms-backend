package bg.mck.authenticationservice.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class User {

    private final Logger logger = LoggerFactory.getLogger(User.class);

    private String id;
    private String email;
    private Set<String> authorities;

    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.authorities = user.getAuthorities();
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.authorities = builder.authorities;
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


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", authorities=" + authorities + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (authorities == null) {
            if (other.authorities != null)
                return false;
        } else if (!authorities.equals(other.authorities))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public static class Builder {
        private String id;
        private String email;
        private Set<String> authorities;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder authorities(Set<String> authorities) {
            this.authorities = authorities;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
