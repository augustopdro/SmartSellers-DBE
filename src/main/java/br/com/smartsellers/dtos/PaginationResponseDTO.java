package br.com.smartsellers.dtos;

import br.com.smartsellers.models.Produto;

import java.util.List;

public record PaginationResponseDTO(
    List<Produto> content,
    int number,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last
) {}
