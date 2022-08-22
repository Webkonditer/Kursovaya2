package com.example.kursovaya2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullableParametrsException extends RuntimeException {
    public NullableParametrsException() {
    }
}
