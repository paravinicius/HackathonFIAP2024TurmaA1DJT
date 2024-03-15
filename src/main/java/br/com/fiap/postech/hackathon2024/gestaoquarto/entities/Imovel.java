package br.com.fiap.postech.hackathon2024.gestaoquarto.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "imoveis")
@NoArgsConstructor
@Getter
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private Localidade localidade;

    @OneToMany(mappedBy = "imovel")
    private List<Quarto> quartos;

    public Imovel(Long id, String nome, List<Quarto> quartos) {
        if (id == null) {
            throw new IllegalArgumentException("O ID deve ser informado.");
        }
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome deve ser informado.");
        }
        if (quartos == null || quartos.isEmpty()) {
            throw new IllegalArgumentException("A lista de quartos deve ser informada.");
        }
        this.id = id;
        this.nome = nome;
        this.quartos = quartos;
    }
}
