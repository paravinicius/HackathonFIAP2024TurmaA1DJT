package br.com.fiap.postech.hackathon2024.GestaoQuarto.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter //Não gerar Setter pois são dados de domínio.
public class Amenidades {

    @NotNull (message = "O ID deve ser informado.")
    private Integer id;
    @NotEmpty(message = "A descrição deve ser informada.")
    private String descricao;

    public Amenidades(Integer id, String descricao) {
        if (id == null) {
            throw new IllegalArgumentException("O ID deve ser informado.");
        }
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição deve ser informada.");
        }
        this.id = id;
        this.descricao = descricao;
    }
}
