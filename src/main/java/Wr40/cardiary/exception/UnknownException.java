package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class UnknownException extends Throwable{

    private HttpStatus status;
    private String message;

    public UnknownException() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "Something went wrong";
    }
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
