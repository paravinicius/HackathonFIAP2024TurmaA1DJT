package br.com.fiap.postech.hackathon2024.gestaoreservas.entitites;

import br.com.fiap.postech.hackathon2024.GestaoQuarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.servicositens.entities.ServicoItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private List<ServicoItem> itensServicos = new ArrayList<>();

    private List<Quarto> quartos = new ArrayList<>();

}
