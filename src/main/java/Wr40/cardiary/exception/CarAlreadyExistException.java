package Wr40.cardiary.exception;

public class CarAlreadyExistException extends RuntimeException  {
    public CarAlreadyExistException() {
        super("This car already exist in repository");
    }
}
