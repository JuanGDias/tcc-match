package com.ufcg.tccmatch.exception;

import org.springframework.http.HttpStatus;

public class AppException extends Exception {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;

    public AppException(HttpStatus notFound) {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
