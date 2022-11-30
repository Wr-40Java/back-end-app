package Wr40.cardiary.exception;/* Created by Alex on 30.11.2022 */

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("This user is already exist");
    }
}
