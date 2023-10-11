package dev.umuterarslan.orderservice.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionDetail {
    private final String message;
    private final HttpStatus httpStatus;
    private final Date timestamp = new Date();

    public ExceptionDetail(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
