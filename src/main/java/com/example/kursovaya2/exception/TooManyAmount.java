package com.example.kursovaya2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooManyAmount extends RuntimeException {
    public TooManyAmount() {
    }

    public TooManyAmount(String message) {
        super(message);
    }

    public TooManyAmount(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyAmount(Throwable cause) {
        super(cause);
    }

    public TooManyAmount(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
