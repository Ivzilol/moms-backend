package bg.mck.ordercommandservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not send the materials to the inventory service.")
public class CouldNotSendToInventoryException extends RuntimeException {
    public CouldNotSendToInventoryException(String message) {
        super(message);
    }
}
