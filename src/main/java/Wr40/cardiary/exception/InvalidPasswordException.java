package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends GlobalException{

    public InvalidPasswordException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Invalid password");
    }
}
