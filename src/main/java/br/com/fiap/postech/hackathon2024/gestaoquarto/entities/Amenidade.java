package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

public enum Amenidade {

    PISCINA_INFANTIL("Piscina infantil"),
    PISCINA_ADULTO("Piscina adulto"),
    RESTAURANTE_SELF_SERVICE("Restaurante self service"),
    RESTAURANTE_A_LA_CARTE("Restaurante a la carte"),
    AREA_KIDS("Área Kids");
    private final String descricao;
    Amenidade(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return descricao;
    }
}
