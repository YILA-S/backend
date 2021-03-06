package backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<?> handleInvalidParameterException(InvalidParameterException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> handleItemNotFoundException(ItemNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
