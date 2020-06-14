package com.leverx.blog.exception.handler;

import com.leverx.blog.exception.FailedAddObjectException;
import com.leverx.blog.exception.FailedDeleteObjectException;
import com.leverx.blog.exception.FailedUpdateObjectException;
import com.leverx.blog.exception.NotFoundObjectException;
import com.leverx.blog.exception.UserException;
import com.leverx.blog.exception.handler.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FailedAddObjectException.class)
    public ResponseEntity<?> handleFailedAddObject(FailedAddObjectException exception) {
        String message = exception.getMessage();
        logger.error(message, exception);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError(message));
    }

    @ExceptionHandler(FailedUpdateObjectException.class)
    public ResponseEntity<?> handleFailedUpdateObject(FailedUpdateObjectException exception) {
        String message = exception.getMessage();
        logger.error(message, exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(message));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUser(UserException exception) {
        String message = exception.getMessage();
        logger.error(message, exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(message));
    }

    @ExceptionHandler(NotFoundObjectException.class)
    public ResponseEntity<?> handleNotFoundObject(NotFoundObjectException exception) {
        String message = exception.getMessage();
        logger.error(message, exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(message));
    }

    @ExceptionHandler(FailedDeleteObjectException.class)
    public ResponseEntity<?> handleFailedDeleteObject(FailedDeleteObjectException exception) {
        String message = exception.getMessage();
        logger.error(message, exception);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError(message));
    }
}
