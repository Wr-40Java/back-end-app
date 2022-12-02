package Wr40.cardiary.exception;

public class WrongEmailAddressException extends RuntimeException {
    public WrongEmailAddressException() {
        super("Wrong email addres");
    }
}
