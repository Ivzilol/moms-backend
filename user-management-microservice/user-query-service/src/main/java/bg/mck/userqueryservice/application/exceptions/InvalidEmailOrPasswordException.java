package bg.mck.userqueryservice.application.exceptions;

public class InvalidEmailOrPasswordException extends RuntimeException {

    public InvalidEmailOrPasswordException(String message) {
        super(message);
    }
}
