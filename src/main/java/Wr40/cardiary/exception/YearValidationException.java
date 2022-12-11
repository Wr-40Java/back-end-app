package Wr40.cardiary.exception;

public class YearValidationException extends RuntimeException{
    public YearValidationException() {
        super("Wrong year value");
    }
}
