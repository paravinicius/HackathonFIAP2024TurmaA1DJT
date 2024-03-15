package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

public enum TipoCama {
    CASAL("Casal"),
    QUEEN_SIZE("Queen Size"),
    SOLTEIRO("Solteiro"),
    BELICHE("Beliche");

    private String descricao;

    TipoCama() {}
    TipoCama(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}