package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public GlobalException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
