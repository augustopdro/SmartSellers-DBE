package br.com.smartsellers.services;


import br.com.smartsellers.dtos.LoginResponseDTO;
import br.com.smartsellers.exceptions.RestNotFoundException;
import br.com.smartsellers.exceptions.RestUnauthorizedException;
import br.com.smartsellers.models.Usuario;
import br.com.smartsellers.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.UUID;

@Service
public class UsuarioService {
	Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	private UsuarioRepository repository;

	 @Autowired
 	public UsuarioService(UsuarioRepository repository)
	 {
		 this.repository = repository;
	 }

	public Usuario cadastrar(Usuario usuario)
    {
		log.info("Cadastrando usuario: " + usuario);

		Usuario savedUsuario = repository.save(usuario);
		String codigoDeAcesso = gerarCodigoDeAcesso(savedUsuario.getId());
		savedUsuario.setCodigoDeAcesso(codigoDeAcesso);

		return repository.save(savedUsuario);
    }


	public Usuario recuperar(long id)
	{
		log.info("Recuperando cadastro de usuario pelo id: " + id);

		Usuario usuario = repository
							.findById(id)
							.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

		return usuario;
	}

	public LoginResponseDTO logar(String codigoDeAcesso)
	{
		log.info("Validando código informado");
		log.info(codigoDeAcesso);

		Usuario usuario = repository
				.buscarPorCodigo(codigoDeAcesso)
				.orElseThrow(() -> new RestUnauthorizedException("Código inválido"));

		String acesso = usuario.getCodigoDeAcesso();
		return new LoginResponseDTO(acesso);
	}


	private String gerarCodigoDeAcesso(Long userId) {
		String seed = userId.toString() + System.currentTimeMillis();
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		SecureRandom random = new SecureRandom(seed.getBytes());
		StringBuilder code = new StringBuilder();

		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(characters.length());
			code.append(characters.charAt(index));
		}

		return code.toString();
	}
}
