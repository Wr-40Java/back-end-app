package Wr40.cardiary.exception;/* Created by Alex on 30.11.2022 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User with this user name is not found");
    }
}
