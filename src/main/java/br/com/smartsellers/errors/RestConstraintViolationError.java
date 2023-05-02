package br.com.smartsellers.errors;

public record RestConstraintViolationError(
        int code,
        Object field,
        String message
) {}
