package com.leverx.blog.exception;

public class FailedUpdateObjectException extends RuntimeException {

    public FailedUpdateObjectException() {
    }

    public FailedUpdateObjectException(String message) {
        super(message);
    }

    public FailedUpdateObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedUpdateObjectException(Throwable cause) {
        super(cause);
    }
}
