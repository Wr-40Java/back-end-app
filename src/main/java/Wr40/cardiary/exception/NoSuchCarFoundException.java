package Wr40.cardiary.exception;

public class NoSuchCarFoundException extends RuntimeException{
    public NoSuchCarFoundException() {
        super("No such car with given VIN number has been found");
    }
}
