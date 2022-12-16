package Wr40.cardiary.handler;

import Wr40.cardiary.exception.*;
import Wr40.cardiary.model.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(CarAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleCarAlreadyExistException(CarAlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(NoSuchCarFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleNoSuchCarFoundException(NoSuchCarFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(YearValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleYearValidationException(YearValidationException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(WrongEmailAddressException.class)
            public final ProblemDetail handleWrongEmailAddressException(WrongEmailAddressException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(TaxTypeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleTaxTypeAlreadyExistsException(TaxTypeAlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(NoSuchEntityFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleNoSuchEntityFound(NoSuchEntityFound e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(InsuranceCompanyAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleExistingInsCompanyNameException(InsuranceCompanyAlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(NoSuchInsuranceCompanyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleNoSuchInsCompanyException(NoSuchInsuranceCompanyException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(NoSuchInsuranceTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleNotExistingInsCompanyTypeException(NoSuchInsuranceTypeException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(NoSuchMaintenanceHistoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleNoSuchMaintenanceHistoryException(NoSuchMaintenanceHistoryException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(MaintenanceEventAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleMaintenanceEventAlreadyExistsException(MaintenanceEventAlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(NoSuchMaintenanceEventFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleNoSuchMaintenanceEventException(NoSuchMaintenanceEventFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(UnableToDeleteMaintenanceEventException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleUnableToDeleteMaintenanceEventException(UnableToDeleteMaintenanceEventException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(SQLExceptionCustomized.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ProblemDetail handleUnableToDeleteInsuranceCompanyAloneException(SQLExceptionCustomized e) {
        return  ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getMessage());
    }


}
