package Wr40.cardiary.handler;

import Wr40.cardiary.exception.*;
import Wr40.cardiary.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CarAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleCarAlreadyExistException(CarAlreadyExistsException e){
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(),e.getMessage(),HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoSuchCarFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleNoSuchCarFoundException(NoSuchCarFoundException e){
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(),e.getMessage(),HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(YearValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleYearValidationException(YearValidationException e){
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(),e.getMessage(),HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(WrongEmailAddressException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleWrongEmailAddressException(WrongEmailAddressException e){
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(),e.getMessage(),HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(TaxTypeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleTaxTypeAlreadyExistsException(TaxTypeAlreadyExistsException e){
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(),e.getMessage(),HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoSuchEntityFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleNoSuchEntityFound(NoSuchEntityFound e){
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(),e.getMessage(),HttpStatus.BAD_REQUEST));
    }
}
