package Wr40.cardiary.exception;

public class MaintenanceEventAlreadyExistsException extends RuntimeException{
    public MaintenanceEventAlreadyExistsException() {
        super("Maintenance event for maintenance history by this name already exists!");
    }
}
