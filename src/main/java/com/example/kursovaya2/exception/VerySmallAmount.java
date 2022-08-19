package com.example.kursovaya2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VerySmallAmount extends RuntimeException {
    public VerySmallAmount() {
    }

    public VerySmallAmount(String message) {
        super(message);
    }

    public VerySmallAmount(String message, Throwable cause) {
        super(message, cause);
    }

    public VerySmallAmount(Throwable cause) {
        super(cause);
    }

    public VerySmallAmount(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
