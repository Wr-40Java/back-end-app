package Wr40.cardiary.exception;

public class TaxNotFoundException extends RuntimeException {
    public TaxNotFoundException() {
        super("No such tax with given id has been found");
    }
}
