package com.comida.exceptions;

public class ComidaAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ComidaAlreadyExistsException(String msg) {
        super(msg);
    }

    public ComidaAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }}
