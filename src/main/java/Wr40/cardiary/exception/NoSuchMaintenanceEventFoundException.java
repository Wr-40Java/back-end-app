package Wr40.cardiary.exception;

public class NoSuchMaintenanceEventFoundException extends RuntimeException{
    public NoSuchMaintenanceEventFoundException() {
        super("No such maintenance event found for given id!");
    }
}
