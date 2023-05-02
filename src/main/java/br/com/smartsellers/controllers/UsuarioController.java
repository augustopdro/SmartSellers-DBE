package br.com.smartsellers.controllers;

import br.com.smartsellers.dtos.LoginResponseDTO;
import br.com.smartsellers.dtos.UsuarioResponseDTO;
import br.com.smartsellers.models.Usuario;
import br.com.smartsellers.services.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("cadastrar")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> cadastrar(@Valid @RequestBody Usuario usuario)
    {
        var usuarioCadastrado = usuarioService.cadastrar(usuario);

        var responseDTO = new UsuarioResponseDTO(usuarioCadastrado.getId(), usuarioCadastrado.getNome(), usuarioCadastrado.getEmail(), usuarioCadastrado.getTelefone(), usuarioCadastrado.getCodigoDeAcesso());

        var entityModel = EntityModel.of(
                responseDTO,
                linkTo(methodOn(UsuarioController.class).cadastrar(usuario)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).logar(usuario.getCodigoDeAcesso())).withRel("logar")
        );

        return ResponseEntity.created(linkTo(methodOn(UsuarioController.class).cadastrar(usuario)).toUri())
                .body(entityModel);
    }


    @PostMapping("login")
    public EntityModel<LoginResponseDTO> logar(@Valid @RequestBody String codigoDeAcesso)
    {
        log.info("solicitando validação das credenciais informadas");

        LoginResponseDTO responseService = usuarioService.logar(codigoDeAcesso);

        return EntityModel.of(
                responseService,
                linkTo(methodOn(UsuarioController.class).logar(codigoDeAcesso)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).cadastrar(new Usuario())).withRel("cadastrar")
        );
    }
}
