package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

//@Entity
@NoArgsConstructor
@Getter
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "O nome deve ser informado.")
    private String nome;
    @NotEmpty
    private List<Quarto> quartos;

    public Imovel(Integer id, String nome, List<Quarto> quartos) {
        if (id == null) {
            throw new IllegalArgumentException("O ID deve ser informado.");
        }
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome deve ser informado.");
        }
        if (quartos == null || quartos.isEmpty()) {
            throw new IllegalArgumentException("A lista de quartos deve ser informada.");
        }
        this.id = id;
        this.nome = nome;
        this.quartos = quartos;
    }
}
