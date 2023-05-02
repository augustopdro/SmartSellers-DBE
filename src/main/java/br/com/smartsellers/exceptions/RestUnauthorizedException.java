package br.com.smartsellers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class RestUnauthorizedException extends RuntimeException {

    public RestUnauthorizedException(String message) {
        super(message);
    }

}