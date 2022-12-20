package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class MaintenanceEventAlreadyExistsException extends GlobalException{
    public MaintenanceEventAlreadyExistsException() {
        super(HttpStatus.NOT_ACCEPTABLE,"Maintenance event for maintenance history by this name already exists!");
    }
}
