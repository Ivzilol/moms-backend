package bg.mck.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Category not found!")
public class InvalidCategoryException extends RuntimeException{

    public InvalidCategoryException(String message) {
        super(message);
    }
}
