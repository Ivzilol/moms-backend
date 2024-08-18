package bg.mck.userqueryservice.application.exceptions;

public class UserIsInActiveException extends RuntimeException {
    public UserIsInActiveException() {
        super("User is inactive!");
    }
}
