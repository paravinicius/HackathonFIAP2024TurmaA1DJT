package br.com.fiap.postech.hackathon2024.gestaoreservas.entitites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @ElementCollection
    private List<Long> itensServicos = new ArrayList<>();

    @ElementCollection
    private List<Long> quartos = new ArrayList<>();

}