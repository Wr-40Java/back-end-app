package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class TaxNotFoundException extends GlobalException {
    public TaxNotFoundException() {
        super(HttpStatus.NOT_FOUND,"No such tax with given id has been found");
    }
}
