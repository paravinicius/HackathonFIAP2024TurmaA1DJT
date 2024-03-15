package br.com.fiap.postech.hackathon2024.gestaoclientes.entities;

import br.com.fiap.postech.hackathon2024.gestaoreservas.entitites.Reserva;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pais_de_origem")
    @NonNull
    private String paisDeOrigem;


    @Column(name = "cpf")
    @NonNull
    private String cpf;

    @Column(name = "passaporte")
    @NonNull
    private String passaporte;

    @Column(name = "nome_completo")
    @NonNull
    private String nomeCompleto;

    @Column(name = "data_nascimento")
    @NonNull
    private LocalDate dataNascimento;

    @Column(name = "endereco_pais_origem")
    @NonNull
    private String enderecoPaisOrigem;

    @Column(name = "telefone")
    @NonNull
    private String telefone;

    @Column(name = "email")
    @NonNull
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Reserva> reservas;
}

