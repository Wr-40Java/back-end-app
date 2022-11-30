package Wr40.cardiary.exception;

public class WrongEmailAddressException extends RuntimeException {
    public WrongEmailAddressException() {
        super("Email you entered is not correct");
    }
}
