package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class UnableToDeleteMaintenanceEventException extends GlobalException {
    public UnableToDeleteMaintenanceEventException() {
        super(HttpStatus.BAD_REQUEST,"Unable to delete maintenance event!");
    }
}
