package bg.mck.exceptions;

public class DuplicatedInventoryItemException extends RuntimeException{

    public DuplicatedInventoryItemException(String message) {
        super(message);
    }
}
