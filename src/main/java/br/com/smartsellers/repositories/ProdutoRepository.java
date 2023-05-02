package br.com.smartsellers.repositories;

import br.com.smartsellers.models.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByIdAndUsuarioId(long registroId, long usuarioId);

    @Query("SELECT r FROM Produto r WHERE r.usuario.id = :usuarioId")
    Page<Produto> getAllRegisters(long usuarioId , Pageable pageable);
}
