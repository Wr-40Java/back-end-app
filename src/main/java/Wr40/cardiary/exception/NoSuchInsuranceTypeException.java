package Wr40.cardiary.exception;

public class NoSuchInsuranceTypeException extends RuntimeException {
    public NoSuchInsuranceTypeException() {
        super("No such insurance type found!");
    }
}
