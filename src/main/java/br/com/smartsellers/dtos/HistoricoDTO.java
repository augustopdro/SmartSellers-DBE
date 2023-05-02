package br.com.smartsellers.dtos;

import br.com.smartsellers.models.Produto;

import java.util.List;

public record HistoricoDTO(List<Produto> produtos) {}

