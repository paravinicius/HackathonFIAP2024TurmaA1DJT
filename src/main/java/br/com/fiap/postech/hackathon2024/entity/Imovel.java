package br.com.fiap.postech.hackathon2024.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class Imovel {

    @NotNull (message = "O ID deve ser informado.")
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
