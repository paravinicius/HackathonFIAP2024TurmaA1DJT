package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Entity
@NoArgsConstructor
@Getter //Não gerar Setter pois são dados de domínio.
public class Comodidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "O nome deve ser informado.")
    private String nome;

    public Comodidade(Integer id, String nome) {
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
