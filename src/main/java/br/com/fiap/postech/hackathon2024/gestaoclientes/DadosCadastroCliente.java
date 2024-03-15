package br.com.fiap.postech.hackathon2024.gestaoclientes;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public record DadosCadastroCliente(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        Long id,

        @Column(name = "pais_de_origem")
        String paisDeOrigem,

        @Column(name = "cpf")
        String cpf,

        @Column(name = "passaporte")
        String passaporte,


        @Column(name = "nome_completo")
        String nomeCompleto,

        @Column(name = "data_nascimento")
        LocalDate dataNascimento,

        @Column(name = "telefone")
        String telefone,

        @Column(name = "email")
        String email
) {}