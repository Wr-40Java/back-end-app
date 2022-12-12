package Wr40.cardiary.exception;

public class NoSuchEntityFound extends RuntimeException{
    public NoSuchEntityFound() {
        super("No such entity found");
    }
}
