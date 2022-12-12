package Wr40.cardiary.exception;

public class NoSuchTechnicalServiceFoundException extends RuntimeException {
    public NoSuchTechnicalServiceFoundException() {
        super("No such maintenance event found for given id!");
    }
}
