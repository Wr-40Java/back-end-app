package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class NoSuchEntityFound extends GlobalException{
    public NoSuchEntityFound() {
        super(HttpStatus.NOT_FOUND,"No such entity found");
    }
}
