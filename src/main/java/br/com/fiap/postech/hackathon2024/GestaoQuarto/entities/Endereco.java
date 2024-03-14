package br.com.fiap.postech.hackathon2024.GestaoQuarto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Entity
@NoArgsConstructor
@Getter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "O logradouro deve ser informado.")
    private String logradouro;
    @NotEmpty(message = "O CEP deve ser informado.")
    @jakarta.validation.constraints.Pattern(
        regexp = "\\d{5}-\\d{3}",
        message = "O CEP deve seguir o formato 00000-000."
    )
    private String CEP;
    @NotEmpty(message = "A cidade deve ser informada.")
    private String cidade;
    @NotEmpty(message = "O estado deve ser informado.")
    private String estado;

    public Endereco(String logradouro, String CEP, String cidade, String estado) {
        if (logradouro == null || logradouro.isEmpty()) {
            throw new IllegalArgumentException("O logradouro deve ser informado.");
        }
        if (CEP == null || CEP.isEmpty() || !java.util.regex.Pattern.matches("\\d{5}-\\d{3}", CEP)) {
            throw new IllegalArgumentException("O CEP deve ser informado e deve seguir o formato 00000-000.");
        }
        if (cidade == null || cidade.isEmpty()) {
            throw new IllegalArgumentException("A cidade deve ser informada.");
        }
        if (estado == null || estado.isEmpty()) {
            throw new IllegalArgumentException("O estado deve ser informado.");
        }
        this.logradouro = logradouro;
        this.CEP = CEP;
        this.cidade = cidade;
        this.estado = estado;
    }
}
