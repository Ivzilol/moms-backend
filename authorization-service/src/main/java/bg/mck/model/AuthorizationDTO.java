package bg.mck.model;

public class AuthorizationDTO {

    private String token;
    private String pathToReach;

    public AuthorizationDTO(String token, String pathToReach) {
        this.token = token;
        this.pathToReach = pathToReach;
    }

    public AuthorizationDTO() {
    }

    public String getToken() {
        return token;
    }

    public AuthorizationDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public String getPathToReach() {
        return pathToReach;
    }

    public AuthorizationDTO setPathToReach(String pathToReach) {
        this.pathToReach = pathToReach;
        return this;
    }
}
