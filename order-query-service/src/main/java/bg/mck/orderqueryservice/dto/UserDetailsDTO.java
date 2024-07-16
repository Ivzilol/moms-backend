package bg.mck.orderqueryservice.dto;

public class UserDetailsDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String token;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(Long id, String email, String firstName, String lastName, String token) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public UserDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetailsDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UserDetailsDTO setToken(String token) {
        this.token = token;
        return this;
    }
}
