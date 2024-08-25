package bg.mck.ordercommandservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Construction site number must not be empty.")
public class ConstructionSiteHasNoNumberException extends RuntimeException {
    public ConstructionSiteHasNoNumberException(String message) {
        super(message);
    }
}
