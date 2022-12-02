package Wr40.cardiary.exception;

public class TaxAlreadyExistException extends RuntimeException {
    public TaxAlreadyExistException() {
        super("This tax is already added to repository");
    }
}
