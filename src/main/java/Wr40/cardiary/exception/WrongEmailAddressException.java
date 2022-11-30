package Wr40.cardiary.exception;/* Created by Alex on 30.11.2022 */

public class WrongEmailAddressException extends RuntimeException {
    public WrongEmailAddressException(){
        super("Email you entered is not correct");
    }
}
