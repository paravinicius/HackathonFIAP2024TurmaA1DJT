package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.*;

public enum Comodidade {

    SOFA("Sofá"),
    POLTRONAS("Poltronas"),
    BOX_COM_DUCHA("Box com Ducha"),
    FRIGOBAR("Frigobar"),
    DUCHA_HIGIENICA("Ducha Higiênica"),
    TV_LED_54_POLS("TV Led 54 polegadas"),
    MESA_DE_ESCRITORIO("Mesa de Escritório"),
    CADEIRA_DE_ESCRITORIO("Cadeira de Escritório");
    private final String descricao;

    Comodidade(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return descricao;
    }
}
