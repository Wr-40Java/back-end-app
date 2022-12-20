package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class NoSuchInsuranceCompanyException extends GlobalException {
    public NoSuchInsuranceCompanyException() {
        super(HttpStatus.NOT_FOUND,"There is no company with given id");
    }
}
