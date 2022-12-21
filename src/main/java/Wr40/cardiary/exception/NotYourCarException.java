package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class NotYourCarException extends GlobalException{
    public NotYourCarException(String message) {
        super(HttpStatus.BAD_REQUEST,message);
    }
}
