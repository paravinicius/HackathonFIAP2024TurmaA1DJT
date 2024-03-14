package br.com.fiap.postech.hackathon2024.GestaoQuarto.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull (message = "A disponibilidade para nova reserva deve ser informada.")
    private Boolean isDisponivelParaNovaReserva;
    @Min (value = 1, message = "A capacidade máxima de pessoas deve ser pelo menos 1.")
    private Integer capacidadeMaximaPessoas;
    @NotNull (message = "O valor da diária deve ser informado.")
    private Double valorDiaria;
    @NotEmpty (message = "A comodidade deve ser informada.")
    private List<Comodidade> comodidades;
    @NotEmpty (message = "A quantidade de camas deve ser informada.")
    @Min (value = 1, message = "A quantidade de camas deve ser pelo menos 1.")
    private List<TipoCama> tiposCamas;
    private TipoQuarto tipoQuarto;

    public Quarto(Integer id, Boolean isDisponivelParaNovaReserva, Integer capacidadeMaximaPessoas, Double valorDiaria, List<Comodidade> comodidades, List<TipoCama> tiposCamas, TipoQuarto tipoQuarto) {
        if (id == null) {
            throw new IllegalArgumentException("O ID deve ser informado.");
        }
        if (isDisponivelParaNovaReserva == null) {
            throw new IllegalArgumentException("A disponibilidade para nova reserva deve ser informada.");
        }
        if (capacidadeMaximaPessoas == null || capacidadeMaximaPessoas < 1) {
            throw new IllegalArgumentException("A capacidade máxima de pessoas deve ser pelo menos 1.");
        }
        if (valorDiaria == null) {
            throw new IllegalArgumentException("O valor da diária deve ser informado.");
        }
        if (comodidades == null || comodidades.isEmpty()) {
            throw new IllegalArgumentException("A lista de comodidades deve ser informada.");
        }
        if (tiposCamas == null || tiposCamas.isEmpty()) {
            throw new IllegalArgumentException("A lista de comodidades deve ser informada.");
        }
        if (tipoQuarto == null) {
            throw new IllegalArgumentException("O tipo quarto deve ser informado");
        }
        this.id = id;
        this.isDisponivelParaNovaReserva = isDisponivelParaNovaReserva;
        this.capacidadeMaximaPessoas = capacidadeMaximaPessoas;
        this.valorDiaria = valorDiaria;
        this.comodidades = comodidades;
        this.tiposCamas = tiposCamas;
        this.tipoQuarto = tipoQuarto;
    }
}
