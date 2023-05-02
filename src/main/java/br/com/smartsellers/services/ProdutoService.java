package br.com.smartsellers.services;

import br.com.smartsellers.exceptions.RestNotFoundException;
import br.com.smartsellers.models.Produto;
import br.com.smartsellers.models.Usuario;
import br.com.smartsellers.repositories.ProdutoRepository;
import br.com.smartsellers.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProdutoService {

    private ProdutoRepository repository;
    private UsuarioRepository usuarioRepository;

    Logger log = LoggerFactory.getLogger(ProdutoService.class);

    public ProdutoService (ProdutoRepository repository, UsuarioRepository usuarioRepository)
    {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public Produto registrarProduto(Produto produto, long userId)
    {
        var usuario = recuperarUsuario(userId);
        produto.setUsuario(usuario);
        usuario.getProduto().add(produto);
        return salvarProduto(produto);
    }

    public Produto atualizarProduto(Produto produto,long id)
    {
        var produtoExistente = recuperarProduto(id);
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());

        return atualizarProduto(produtoExistente);
    }

    @Transactional
    public void deletarProduto(long userId, long produtoId)
    {
        var usuario = recuperarUsuario(userId);

        var produto = recuperarProduto(produtoId);

        if (!produto.getUsuario().equals(usuario)) {
            log.info("getid: " + produto.getUsuario().getId());
            throw new RestNotFoundException("Produto informado não pertence ao Usuário informado");
        }

        usuario.getProduto().remove(produto);
        usuarioRepository.save(usuario);
        repository.delete(produto);
    }

    private Usuario recuperarUsuario(long userId)
    {
        log.info("Recuperando usuario com id: " + userId);

        Usuario usuario = usuarioRepository
                .findById(userId)
                .orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

        return usuario;
    }

    private Produto salvarProduto(Produto produto)
    {
        log.info("Registrando um produto: " + produto);
        return repository.save(produto);
    }

    private Produto recuperarProduto(long produtoId)
    {
        log.info("Recuperando o produto com id: " + produtoId);

        Produto produto = repository
                .findById(produtoId)
                .orElseThrow(() -> new RestNotFoundException("Produto não encontrado"));

        return produto;
    }

    private Produto atualizarProduto(Produto produto)
    {
        log.info("Atualizando o produto com id: " + produto.getId());

        return repository.save(produto);
    }

}
