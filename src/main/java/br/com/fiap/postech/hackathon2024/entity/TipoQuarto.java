package br.com.fiap.postech.hackathon2024.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter //Não gerar Setter pois são dados de domínio.
public class TipoQuarto {

    @NotNull (message = "O ID deve ser informado.")
    private Integer id;
    @NotEmpty(message = "O nome deve ser informado.")
    private String nome;

    public TipoQuarto(Integer id, String nome) {
        if (id == null) {
            throw new IllegalArgumentException("O ID deve ser informado.");
        }
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome deve ser informado.");
        }
        this.id = id;
        this.nome = nome;
    }
}