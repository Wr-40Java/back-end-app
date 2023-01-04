package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class InsuranceCompanyAlreadyExistsException extends GlobalException{
    public InsuranceCompanyAlreadyExistsException() {
        super(HttpStatus.NOT_ACCEPTABLE,"Insurance company by this name already exists!");
    }
}
