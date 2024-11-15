package bg.mck.ordercommandservice.exceptionHandling;

import bg.mck.ordercommandservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(ErrorMapper.mapValidationError(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstructionSiteNotFoundException.class)
    public ResponseEntity<String> handleConstructionSiteNotFoundException(ConstructionSiteNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstructionSiteAlreadyExists.class)
    public ResponseEntity<String> handleConstructionSiteAlreadyExists(ConstructionSiteAlreadyExists e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstructionSiteHasNoNameException.class)
    public ResponseEntity<String> handleConstructionSiteHasNoNameException(ConstructionSiteHasNoNameException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstructionSiteHasNoNumberException.class)
    public ResponseEntity<String> handleConstructionSiteHasNoNumberException(ConstructionSiteHasNoNumberException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}





