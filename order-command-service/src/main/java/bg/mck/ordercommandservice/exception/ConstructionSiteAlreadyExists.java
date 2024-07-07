package bg.mck.ordercommandservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Construction site already exists")
public class ConstructionSiteAlreadyExists extends RuntimeException {
    public ConstructionSiteAlreadyExists(String message) {
        super(message);
    }
}
