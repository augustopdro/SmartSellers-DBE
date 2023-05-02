package br.com.smartsellers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(
    @NotNull
    @NotBlank
    @Email
    String email, 

    @NotNull 
    @NotBlank
    String senha
) {}
