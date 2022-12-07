package Wr40.cardiary.exception;

public class TaxTypeAlreadyExistsException extends RuntimeException{
    public TaxTypeAlreadyExistsException() {
        super("This Tax Type already exists");
    }
}
