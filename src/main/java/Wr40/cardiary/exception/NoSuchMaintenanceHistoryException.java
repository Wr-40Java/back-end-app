package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class NoSuchMaintenanceHistoryException extends GlobalException{
    public NoSuchMaintenanceHistoryException() {
        super(HttpStatus.NOT_FOUND,"No such maintenance history found for given id!");
    }
}
