package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class TaxTypeAlreadyExistsException extends GlobalException{
    public TaxTypeAlreadyExistsException() {
        super(HttpStatus.NOT_ACCEPTABLE,"This Tax Type already exists");
    }
}
