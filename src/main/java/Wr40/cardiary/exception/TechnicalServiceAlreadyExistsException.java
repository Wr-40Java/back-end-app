package Wr40.cardiary.exception;

public class TechnicalServiceAlreadyExistsException extends RuntimeException {
    public TechnicalServiceAlreadyExistsException(){
        super("Technical service for maintenance history by this name already exists");
    }
}
