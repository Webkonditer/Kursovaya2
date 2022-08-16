package com.example.kursovaya2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class SameQuestionException extends RuntimeException {
    public SameQuestionException() {
    }

    public SameQuestionException(String message) {
        super(message);
    }

    public SameQuestionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameQuestionException(Throwable cause) {
        super(cause);
    }

    public SameQuestionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
