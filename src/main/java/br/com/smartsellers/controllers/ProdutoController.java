package br.com.smartsellers.controllers;

import br.com.smartsellers.dtos.PaginationResponseDTO;
import br.com.smartsellers.exceptions.RestBadRequestException;
import br.com.smartsellers.models.Produto;
import br.com.smartsellers.services.HistoricoService;
import br.com.smartsellers.services.ProdutoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private ProdutoService produtoService;
    private HistoricoService historicoService;
    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    public ProdutoController(ProdutoService produtoService, HistoricoService historicoService) {
        this.produtoService = produtoService;
        this.historicoService = historicoService;
    }



    @PostMapping("{userId}/registrar")
    public ResponseEntity<EntityModel<Produto>> cadastrarProduto(@Valid @RequestBody Produto produto, @PathVariable long userId) {
        log.info("Cadastrando produto");
        var responseService = produtoService.registrarProduto(produto, userId);

        var entityModel = EntityModel.of(
                responseService,
                linkTo(methodOn(ProdutoController.class).cadastrarProduto(produto, userId)).withSelfRel(),
                linkTo(methodOn(ProdutoController.class).atualizarProduto(produto, userId)).withRel("atualizar"),
                linkTo(methodOn(ProdutoController.class).deletarProduto(userId, responseService.getId())).withRel("deletar")
        );

        return ResponseEntity.created(linkTo(methodOn(ProdutoController.class).cadastrarProduto(produto, userId)).toUri())
                .body(entityModel);
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Produto>> atualizarProduto(@RequestBody Produto produto, @PathVariable long userId)
    {
        log.info("Atualizando produto de usuario pelo id: " + userId);

        var retornoService = produtoService.atualizarProduto(produto, userId);
        if(retornoService == null)
            throw new RestBadRequestException("Atualização não efetuada. Tente novamente com dados diferentes.");

        var produtoAtualizado = EntityModel.of(
                retornoService,
                linkTo(methodOn(ProdutoController.class).atualizarProduto(produto, userId)).withSelfRel(),
                linkTo(methodOn(ProdutoController.class).cadastrarProduto(produto, userId)).withRel("cadastrar"),
                linkTo(methodOn(ProdutoController.class).deletarProduto(userId, produto.getId())).withRel("deletar")
        );

        return ResponseEntity.ok(produtoAtualizado);
    }


    @DeleteMapping("{userId}/deletar/{registroId}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable long userId, @PathVariable long produtoId)
    {
        log.info("Deletando registro de sono");

        produtoService.deletarProduto(userId, produtoId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("{userId}/historico")
    public ResponseEntity<EntityModel<PaginationResponseDTO>> recuperarHistorico(@PathVariable long userId, @PageableDefault(size = 3) Pageable pageable) {
        log.info("Buscando historico");

        var historicoEncontrado = historicoService.recuperarHistorico(userId, pageable);

        var entityModel = EntityModel.of(
                historicoEncontrado,
                linkTo(methodOn(ProdutoController.class).recuperarHistorico(userId, pageable)).withSelfRel());

        return ResponseEntity.ok(entityModel);
    }
}
