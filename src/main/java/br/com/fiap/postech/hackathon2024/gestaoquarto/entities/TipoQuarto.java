package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_quarto")
public enum TipoQuarto {

    STANDARD("Standard"),
    LUXO("Luxo"),
    DUPLEX("Duplex"),
    TRIPLEX("Triplex"),
    INDIVIDUAL("Individual");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    TipoQuarto(String descricao) {
        this.descricao = descricao;
    }

    TipoQuarto() {

    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}