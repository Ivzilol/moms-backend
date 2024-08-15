package bg.mck.usercommandservice.application.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Invalid token for reset password provided.");
    }
}
