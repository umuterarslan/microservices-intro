package dev.umuterarslan.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductQuantityBiggerThanStockException.class)
    public ResponseEntity<?> handleDataSaveException(ProductQuantityBiggerThanStockException exception) {
        ExceptionDetail detail = new ExceptionDetail(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(detail, detail.getHttpStatus());
    }
}
