package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comodidades")
public enum Comodidade {

    SOFA("Sofá"),
    POLTRONAS("Poltronas"),
    BOX_COM_DUCHA("Box com Ducha"),
    FRIGOBAR("Frigobar"),
    DUCHA_HIGIENICA("Ducha Higiênica"),
    TV_LED_54_POLS("TV Led 54 polegadas"),
    MESA_DE_ESCRITORIO("Mesa de Escritório"),
    CADEIRA_DE_ESCRITORIO("Cadeira de Escritório");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    Comodidade() {}

    Comodidade(String descricao) {
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
