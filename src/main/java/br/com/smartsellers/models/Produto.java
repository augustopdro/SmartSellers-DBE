package br.com.smartsellers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 500, message = "A descrição deve ter tamanho de no minimo 10 e no maximo 500 caracteres")
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private Double preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    public Produto(String nome, String descricao, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nDescricao: " + descricao + "\nPreco: " + preco;
    }
}
