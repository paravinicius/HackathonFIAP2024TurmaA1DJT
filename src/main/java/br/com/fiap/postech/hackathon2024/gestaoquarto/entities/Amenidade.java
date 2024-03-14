package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter //Não gerar Setter pois são dados de domínio.
@Table(name = "amenidades")
public enum Amenidade {

    PISCINA_INFANTIL("Piscina infantil"),
    PISCINA_ADULTO("Piscina adulto"),
    RESTAURANTE_SELF_SERVICE("Restaurante self service"),
    RESTAURANTE_A_LA_CARTE("Restaurante a la carte"),
    AREA_KIDS("Área Kids");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    Amenidade() {}

    Amenidade(String descricao) {
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
