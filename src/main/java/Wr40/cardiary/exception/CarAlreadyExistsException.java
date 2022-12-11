package Wr40.cardiary.exception;

public class CarAlreadyExistsException extends RuntimeException  {
    public CarAlreadyExistsException() {
        super("This car already exist in repository");
    }
}
