package bg.mck.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid event type!")
public class InvalidEventTypeException extends RuntimeException{

    public InvalidEventTypeException(String message) {
        super(message);
    }
}
