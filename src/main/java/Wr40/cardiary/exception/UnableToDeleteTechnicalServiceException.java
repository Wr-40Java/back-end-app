package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class UnableToDeleteTechnicalServiceException extends GlobalException {
    public UnableToDeleteTechnicalServiceException() {
        super(HttpStatus.BAD_REQUEST,"Unable to delete technical service.");
    }
}
