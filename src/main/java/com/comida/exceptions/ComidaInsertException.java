package com.comida.exceptions;

public class ComidaInsertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ComidaInsertException(String msg) {
        super(msg);
    }

    public ComidaInsertException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
