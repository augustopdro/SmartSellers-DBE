package br.com.smartsellers.errors;

public record RestValidationError(
        Integer code,
        String field,
        String message
) {}
