package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class NoSuchCarFoundException extends GlobalException{
    public NoSuchCarFoundException() {
        super(HttpStatus.NOT_FOUND,"No such car with given VIN number has been found");
    }
}
