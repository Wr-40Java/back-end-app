package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistedException extends GlobalException {
    public UserAlreadyExistedException() {
        super(HttpStatus.NOT_ACCEPTABLE,"User with given username already exists");
    }
}
