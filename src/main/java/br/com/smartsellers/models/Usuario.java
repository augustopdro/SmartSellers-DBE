package br.com.smartsellers.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z\s]{3,}$", message = "Nome deve conter no mínimo 3 caracteres e nenhum pode ser numérico")
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email só pode conter caracteres alfanuméricos e os especiais: _ . - @")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @NotBlank 
    @Size(min = 11, max = 11, message = "O telefone deve ter tamanho de 11 caracteres")
    @Column(nullable = false)
    private String telefone;

    @Column(unique = true)
    private String codigoDeAcesso;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<Produto> produto;


    public Usuario(String nome, String email, String telefone) {
        if(!setNome(nome))
            throw new IllegalArgumentException("Nome inválido");

        if(!setEmail(email))
            throw new IllegalArgumentException("Email inválido");

        if(!setSenha(telefone))
            throw new IllegalArgumentException("Telefone inválido");
    }

    public boolean setNome(String novoNome) {
        if(validarNome(novoNome))
        {
            nome = novoNome;
            return true;
        }

        return false;
    }

    public boolean setEmail(String novoEmail) {
        if(validarEmail(novoEmail))
        {
            email = novoEmail;
            return true;
        }

        return false;
    }

    public boolean setSenha(String novoTelefone) {
        if(validarSenha(novoTelefone))
        {
            telefone = novoTelefone;
            return true;
        }

        return false;
    }

    private boolean validarNome(String nome)
    {
        String regex = "^(?=.*[a-zA-Z])[a-zA-Z\s]{3,}$";
        java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(regex);

        return padrao.matcher(nome).matches();
    }

    private boolean validarSenha(String senha)
    {
        return senha.length() == 11;
    }

    private boolean validarEmail(String email)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(regex);

        return padrao.matcher(email).matches();
    }
}
