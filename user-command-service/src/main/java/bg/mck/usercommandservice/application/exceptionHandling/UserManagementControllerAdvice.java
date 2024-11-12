package bg.mck.usercommandservice.application.exceptionHandling;


import bg.mck.usercommandservice.application.dto.ResetPasswordDTO;
import bg.mck.usercommandservice.application.exceptions.*;
import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class UserManagementControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception e, HttpServletResponse response) {
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(response.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            try {
                fieldName = ((FieldError) error).getField();
            } catch (ClassCastException ex) {
                fieldName = error.getObjectName();
            }
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException e) {
        HttpStatus status = HttpStatus.resolve(e.status());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        String errorMessage = extractErrorMessage(e.getMessage());
        return new ResponseEntity<>("Feign client error: " + errorMessage, status);
    }

    private String extractErrorMessage(String fullMessage) {
        int lastColonIndex = fullMessage.lastIndexOf(":");
        if (lastColonIndex != -1 && lastColonIndex < fullMessage.length() - 1) {
            return fullMessage.substring(lastColonIndex + 1).trim();
        }
        return "Requested resource not found.";
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<String> handleInvalidEmailException(EmailNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> handleInvalidTokenForResetPasswordException(InvalidTokenException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResetPasswordAlreadySendException.class)
    public ResponseEntity<String> handleInvalidAlreadySendResetPasswordException(ResetPasswordAlreadySendException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

