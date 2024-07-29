package bg.mck.orderqueryservice.advice;

import bg.mck.orderqueryservice.exception.ConstructionSiteNotFoundException;
import bg.mck.orderqueryservice.exception.OrderNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderAdvice {


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGlobalException(Exception e, HttpServletResponse response) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(response.getStatus()));
//    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex, HttpServletResponse response) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(response.getStatus()));
    }

    @ExceptionHandler(ConstructionSiteNotFoundException.class)
    public ResponseEntity<String> handleConstructionSiteNotFoundException(ConstructionSiteNotFoundException ex, HttpServletResponse response) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(response.getStatus()));
    }
}
