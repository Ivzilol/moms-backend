package bg.mck.ordercommandservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such construction site")
public class ConstructionSiteNotFoundException extends RuntimeException {
    public ConstructionSiteNotFoundException(String message) {
        super(message);
    }
}
