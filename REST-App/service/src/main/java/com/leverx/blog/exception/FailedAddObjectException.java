package com.leverx.blog.exception;

public class FailedAddObjectException extends RuntimeException {

    public FailedAddObjectException() {
    }

    public FailedAddObjectException(String message) {
        super(message);
    }

    public FailedAddObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedAddObjectException(Throwable cause) {
        super(cause);
    }
}
