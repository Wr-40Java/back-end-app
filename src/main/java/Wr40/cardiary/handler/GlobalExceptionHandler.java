package Wr40.cardiary.handler;

import Wr40.cardiary.exception.*;
import Wr40.cardiary.model.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(CarAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleCarAlreadyExistException(CarAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoSuchCarFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleNoSuchCarFoundException(NoSuchCarFoundException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(YearValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleYearValidationException(YearValidationException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(WrongEmailAddressException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleWrongEmailAddressException(WrongEmailAddressException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(TaxTypeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleTaxTypeAlreadyExistsException(TaxTypeAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoSuchEntityFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleNoSuchEntityFound(NoSuchEntityFound e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(InsuranceCompanyAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleExistingInsCompanyNameException(InsuranceCompanyAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoSuchInsuranceCompanyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleNoSuchInsCompanyException(NoSuchInsuranceCompanyException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoSuchInsuranceTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleNotExistingInsCompanyTypeException(NoSuchInsuranceTypeException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoSuchMaintenanceHistoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleNoSuchMaintenanceHistoryException(NoSuchMaintenanceHistoryException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MaintenanceEventAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleMaintenanceEventAlreadyExistsException(MaintenanceEventAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleValidationErrors(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), errors.toString(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoSuchMaintenanceEventFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleNoSuchMaintenanceEventException(NoSuchMaintenanceEventFoundException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(UnableToDeleteMaintenanceEventException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleUnableToDeleteMaintenanceEventException(UnableToDeleteMaintenanceEventException e) {
        return ResponseEntity.badRequest().body(new ErrorDetails(LocalDateTime.now(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }


}
