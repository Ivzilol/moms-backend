package bg.mck.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Inventory item not found!")
public class InventoryItemNotFoundException extends RuntimeException{

    public InventoryItemNotFoundException(String message) {
        super(message);
    }
}
