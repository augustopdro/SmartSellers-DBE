package br.com.smartsellers.services;

import br.com.smartsellers.dtos.PaginationResponseDTO;
import br.com.smartsellers.repositories.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

    Logger log = LoggerFactory.getLogger(HistoricoService.class);
    private ProdutoRepository produtoRepository;

    @Autowired
    public HistoricoService(ProdutoRepository produtoRepository)
    {
        this.produtoRepository = produtoRepository;
    }

    public PaginationResponseDTO recuperarHistorico(long userId, Pageable pageable)
    {
        log.info("Buscando historico de sono do usuário: " + userId);

        var registros = produtoRepository.getAllRegisters(userId, pageable);

        return new PaginationResponseDTO(
                registros.getContent(),
                registros.getNumber(),
                registros.getTotalElements(),
                registros.getTotalPages(),
                registros.isFirst(),
                registros.isLast()
        );
    }
}
