package Wr40.cardiary.handler;

import Wr40.cardiary.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public final ProblemDetail handleCarAlreadyExistException(CarAlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistedException.class)
    public final ProblemDetail handleUserAlreadyExistedException(UserAlreadyExistedException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(NoSuchCarFoundException.class)
    public final ProblemDetail handleNoSuchCarFoundException(NoSuchCarFoundException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(YearValidationException.class)
    public final ProblemDetail handleYearValidationException(YearValidationException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(WrongEmailAddressException.class)
    public final ProblemDetail handleWrongEmailAddressException(WrongEmailAddressException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(TaxTypeAlreadyExistsException.class)
    public final ProblemDetail handleTaxTypeAlreadyExistsException(TaxTypeAlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(NoSuchEntityFound.class)
    public final ProblemDetail handleNoSuchEntityFound(NoSuchEntityFound e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(InsuranceCompanyAlreadyExistsException.class)
    public final ProblemDetail handleExistingInsCompanyNameException(InsuranceCompanyAlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(NoSuchInsuranceCompanyException.class)
    public final ProblemDetail handleNoSuchInsCompanyException(NoSuchInsuranceCompanyException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(NoSuchInsuranceTypeException.class)
    public final ProblemDetail handleNotExistingInsCompanyTypeException(NoSuchInsuranceTypeException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(NoSuchMaintenanceHistoryException.class)
    public final ProblemDetail handleNoSuchMaintenanceHistoryException(NoSuchMaintenanceHistoryException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(MaintenanceEventAlreadyExistsException.class)
    public final ProblemDetail handleMaintenanceEventAlreadyExistsException(MaintenanceEventAlreadyExistsException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(NoSuchMaintenanceEventFoundException.class)
    public final ProblemDetail handleNoSuchMaintenanceEventException(NoSuchMaintenanceEventFoundException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(UnableToDeleteMaintenanceEventException.class)
    public final ProblemDetail handleUnableToDeleteMaintenanceEventException(UnableToDeleteMaintenanceEventException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(SQLExceptionCustomized.class)
    public final ProblemDetail handleUnableToDeleteInsuranceCompanyAloneException(SQLExceptionCustomized e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(WrongPhoneNumberException.class)
    public final ProblemDetail handleWrongPhoneNumberExceptionException(WrongPhoneNumberException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public final ProblemDetail handleInvalidPasswordException(InvalidPasswordException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatus(),e.getMessage());
    }



}
