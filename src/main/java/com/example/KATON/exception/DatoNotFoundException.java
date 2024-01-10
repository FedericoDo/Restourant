package com.example.KATON.exception;

public class DatoNotFoundException extends RuntimeException  {
    public DatoNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
