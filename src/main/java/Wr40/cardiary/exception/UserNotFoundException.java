package Wr40.cardiary.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("This user is not found");
    }
}
