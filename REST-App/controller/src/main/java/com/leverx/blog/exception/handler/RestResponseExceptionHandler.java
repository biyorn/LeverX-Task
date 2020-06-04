package com.leverx.blog.exception.handler;

import com.leverx.blog.exception.FailedAddObjectException;
import com.leverx.blog.exception.FailedUpdateObjectException;
import com.leverx.blog.exception.handler.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FailedAddObjectException.class)
    public ResponseEntity<Object> handleFailedAddObject(FailedAddObjectException exception) {
        String message = exception.getMessage();
        logger.error(message, exception);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError(message));
    }

    @ExceptionHandler(FailedUpdateObjectException.class)
    public ResponseEntity<Object> handleFailedUpdateObject(FailedUpdateObjectException exception) {
        String message = exception.getMessage();
        logger.error(message, exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(message));
    }

}
