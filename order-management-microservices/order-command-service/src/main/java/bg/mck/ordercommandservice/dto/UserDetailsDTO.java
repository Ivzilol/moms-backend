package bg.mck.ordercommandservice.dto;

public class UserDetailsDTO {
    private Long id;
    private String username;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public UserDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetailsDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
