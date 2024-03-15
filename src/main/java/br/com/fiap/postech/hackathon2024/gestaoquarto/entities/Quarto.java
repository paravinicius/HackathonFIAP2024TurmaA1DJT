package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "quarto")
@Getter
@Setter
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer capacidadeMaximaPessoas;

    private BigDecimal valorDiaria;

    @Enumerated(EnumType.ORDINAL)
    private List<Comodidade> comodidades;

    @Enumerated(EnumType.ORDINAL)
    private List<TipoCama> tiposCamas;

    @Enumerated(EnumType.ORDINAL)
    private TipoQuarto tipoQuarto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    private Boolean isDisponivelParaNovaReserva;

    @ElementCollection
    @CollectionTable(name = "quartos_datas_ocupadas", joinColumns = @JoinColumn(name = "quarto_id"))
    @Column(name = "data_ocupada")
    private List<LocalDate> datasOcupadas;

    public Quarto(Integer id, Boolean isDisponivelParaNovaReserva, Integer capacidadeMaximaPessoas, BigDecimal valorDiaria, List<Comodidade> comodidades, List<TipoCama> tiposCamas, TipoQuarto tipoQuarto) {
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

    public boolean isOcupado(LocalDate dataInicio, LocalDate dataFim) {
        for (LocalDate data = dataInicio; !data.isAfter(dataFim); data = data.plusDays(1)) {
            if (datasOcupadas.contains(data)) {
                return true;
            }
        }
        return false;
    }
}