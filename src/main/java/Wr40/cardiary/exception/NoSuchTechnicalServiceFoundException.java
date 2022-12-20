package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class NoSuchTechnicalServiceFoundException extends GlobalException {
    public NoSuchTechnicalServiceFoundException() {
        super(HttpStatus.NOT_FOUND,"No such maintenance event found for given id!");
    }
}
