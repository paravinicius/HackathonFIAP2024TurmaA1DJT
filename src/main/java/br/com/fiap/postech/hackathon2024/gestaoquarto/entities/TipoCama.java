package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_cama")
public enum TipoCama {
    CASAL("Casal"),
    QUEEN_SIZE("Queen Size"),
    SOLTEIRO("Solteiro"),
    BELICHE("Beliche");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    TipoCama() {}
    TipoCama(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}