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

		String codigoDeAcesso = gerarCodigoDeAcesso(usuario.getId());
		usuario.setCodigoDeAcesso(codigoDeAcesso);

		return repository.save(usuario);
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
		/*
			Nas próximas atualizações do projeto, usaremos JWT ou alguma outra estratégia
			de acordo com os conteúdos que forem vistos na aula. No momento, optamos em realizar
			o login usando o código de acesso e retornar o id, e com esse ID a aplicação
			que consome tem acesso aos demais endpoints que necessitam do id do usuário
		*/
		log.info("Validando código informado");

		Usuario usuario = repository
				.buscarPorCodigo(codigoDeAcesso)
				.orElseThrow(() -> new RestUnauthorizedException("Código inválido"));

		long acesso = usuario.getId();
		return new LoginResponseDTO(acesso);
	} 


	private String gerarCodigoDeAcesso(Long userId)
	{
		String seed = userId.toString() + "_" + UUID.randomUUID().toString();
		SecureRandom random = new SecureRandom(seed.getBytes());
		byte[] bytes = new byte[16];
		random.nextBytes(bytes);
		UUID uuid = UUID.nameUUIDFromBytes(bytes);
		return uuid.toString();
	}
}
