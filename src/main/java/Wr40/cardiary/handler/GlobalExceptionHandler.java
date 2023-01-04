package Wr40.cardiary.handler;

import Wr40.cardiary.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public final ProblemDetail handleRuntimeExceptions(GlobalException e) {
        log.info("Error was thrown: {}, {}", e.getMessage(),e.getStatus());
        return ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public final ProblemDetail handleUnknownException(Throwable e) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
    }

}
