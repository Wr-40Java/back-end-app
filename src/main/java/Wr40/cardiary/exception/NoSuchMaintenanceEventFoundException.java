package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class NoSuchMaintenanceEventFoundException extends GlobalException{
    public NoSuchMaintenanceEventFoundException() {
        super(HttpStatus.NOT_FOUND,"No such maintenance event found for given id!");
    }
}
