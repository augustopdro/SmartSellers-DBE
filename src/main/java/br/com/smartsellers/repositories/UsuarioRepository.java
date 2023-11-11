package br.com.smartsellers.repositories;

import br.com.smartsellers.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u from Usuario u where u.codigoDeAcesso = ?1")
    Optional<Usuario> buscarPorCodigo(String codigoDeAcesso);
}
