package com.example.kursovaya2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullableParametrsException extends RuntimeException {
    public NullableParametrsException() {
    }

    public NullableParametrsException(String message) {
        super(message);
    }

    public NullableParametrsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullableParametrsException(Throwable cause) {
        super(cause);
    }

    public NullableParametrsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
