package com.leverx.blog.exception;

public class NotFoundObjectException extends RuntimeException {

    public NotFoundObjectException() {
    }

    public NotFoundObjectException(String message) {
        super(message);
    }

    public NotFoundObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundObjectException(Throwable cause) {
        super(cause);
    }
}
