package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class WrongPhoneNumberException extends GlobalException{
    public WrongPhoneNumberException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Wrong phone number");
    }
}
