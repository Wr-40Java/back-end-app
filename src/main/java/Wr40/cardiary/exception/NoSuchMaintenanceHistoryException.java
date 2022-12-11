package Wr40.cardiary.exception;

public class NoSuchMaintenanceHistoryException extends RuntimeException{
    public NoSuchMaintenanceHistoryException() {
        super("No such maintenance history found for given id!");
    }
}
