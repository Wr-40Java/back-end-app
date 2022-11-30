package Wr40.cardiary.exception;

public class UserAlreadyExistedException extends RuntimeException {
    public UserAlreadyExistedException() {
        super("User is already existed");
    }
}
