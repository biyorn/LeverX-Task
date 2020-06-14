package com.leverx.blog.exception;

public class FailedDeleteObjectException extends RuntimeException {

    public FailedDeleteObjectException() {
    }

    public FailedDeleteObjectException(String message) {
        super(message);
    }

    public FailedDeleteObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedDeleteObjectException(Throwable cause) {
        super(cause);
    }
}
