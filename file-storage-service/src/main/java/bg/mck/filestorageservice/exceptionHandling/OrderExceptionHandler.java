package bg.mck.filestorageservice.exceptionHandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        System.out.println(e.getMessage());
    }
}





