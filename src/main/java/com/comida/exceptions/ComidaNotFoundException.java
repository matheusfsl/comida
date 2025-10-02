package com.comida.exceptions;

public class ComidaNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ComidaNotFoundException(String msg) {
        super(msg);
    }

    public ComidaNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
