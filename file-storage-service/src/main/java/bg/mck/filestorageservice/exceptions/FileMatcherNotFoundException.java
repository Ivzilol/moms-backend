package bg.mck.filestorageservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The file has no matching pattern")
public class FileMatcherNotFoundException extends RuntimeException {
    public FileMatcherNotFoundException(String message) {
        super(message);
    }
}
