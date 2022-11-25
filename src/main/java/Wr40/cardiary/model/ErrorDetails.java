package Wr40.cardiary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDetails {
    LocalDateTime timestamp;
    String message;
    HttpStatus status;
}
