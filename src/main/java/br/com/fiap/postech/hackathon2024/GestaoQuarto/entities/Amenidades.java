package br.com.fiap.postech.hackathon2024.GestaoQuarto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter //Não gerar Setter pois são dados de domínio.
public class Amenidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
