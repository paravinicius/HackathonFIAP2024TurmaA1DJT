package br.com.fiap.postech.hackathon2024.gestaoquarto.services;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Endereco;
import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.repositories.EnderecoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    private final EnderecoRepository enderecoRepository;

    @Autowired
    private final EntityManager entityManager;

    public EnderecoService(EnderecoRepository enderecoRepository, EntityManager entityManager) {
        this.enderecoRepository = enderecoRepository;
        this.entityManager = entityManager;
    }

    public Endereco criarQuarto(Endereco endereco){  return enderecoRepository.save(endereco); }

}
