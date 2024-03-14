package br.com.fiap.postech.hackathon2024.servicositens.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
public class ServicoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal valor;

    public ServicoItem() {}

    public ServicoItem(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }
}
