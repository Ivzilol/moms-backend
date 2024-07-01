package bg.mck.userqueryservice.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid email or password")
public class InvalidEmailOrPasswordException extends RuntimeException {

    public InvalidEmailOrPasswordException(String message) {
        super(message);
    }
}
