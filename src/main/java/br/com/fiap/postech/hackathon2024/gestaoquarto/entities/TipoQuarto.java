package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.*;
public enum TipoQuarto {

    STANDARD("Standard"),
    LUXO("Luxo"),
    DUPLEX("Duplex"),
    TRIPLEX("Triplex"),
    INDIVIDUAL("Individual");

    private final String descricao;

    TipoQuarto(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}