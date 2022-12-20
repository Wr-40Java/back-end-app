package Wr40.cardiary.exception;

import org.springframework.http.HttpStatus;

public class CarAlreadyExistsException extends GlobalException  {

    public CarAlreadyExistsException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Car with given VIN number already exists");
    }
}
