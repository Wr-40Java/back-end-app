package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class YearValidationException extends GlobalException{
    public YearValidationException() {
        super(HttpStatus.NOT_ACCEPTABLE,"Given year is not correct");
    }
}
