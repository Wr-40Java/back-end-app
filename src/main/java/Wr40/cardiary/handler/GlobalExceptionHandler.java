package Wr40.cardiary.handler;

import Wr40.cardiary.exception.CarAlreadyExistException;
import Wr40.cardiary.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CarAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleCarAlreadyExistException(CarAlreadyExistException e){
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(),e.getMessage(),HttpStatus.BAD_REQUEST));
    }
}
