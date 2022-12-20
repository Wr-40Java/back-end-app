package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class NoSuchInsuranceTypeException extends GlobalException {
    public NoSuchInsuranceTypeException() {
        super(HttpStatus.NOT_FOUND,"No such insurance type found!");
    }
}
