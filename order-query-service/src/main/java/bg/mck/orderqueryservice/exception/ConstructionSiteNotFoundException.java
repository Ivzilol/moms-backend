package bg.mck.orderqueryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConstructionSiteNotFoundException extends RuntimeException {
    public ConstructionSiteNotFoundException(String msg) {
        super(msg);
    }
}
