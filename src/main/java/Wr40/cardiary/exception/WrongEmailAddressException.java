package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class WrongEmailAddressException extends GlobalException {
    public WrongEmailAddressException() {
        super(HttpStatus.BAD_REQUEST,"Wrong email addres");
    }
}
