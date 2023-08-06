package dev.umuterarslan.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DataSaveException extends RuntimeException{
    public DataSaveException(String message) {
        super(message);
    }
}
