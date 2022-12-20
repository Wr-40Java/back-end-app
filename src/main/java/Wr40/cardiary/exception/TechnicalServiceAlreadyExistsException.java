package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class TechnicalServiceAlreadyExistsException extends GlobalException {
    public TechnicalServiceAlreadyExistsException(){
        super(HttpStatus.NOT_ACCEPTABLE,"Technical service for maintenance history by this name already exists");
    }
}
