package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class SQLExceptionCustomized extends GlobalException{
    public SQLExceptionCustomized(String message) {
        super(HttpStatus.BAD_REQUEST,message);
    }
}
