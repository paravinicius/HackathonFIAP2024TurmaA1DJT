package br.com.fiap.postech.hackathon2024.gestaoquarto.services;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Endereco;
import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.repositories.EnderecoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Endereco> buscarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    public Endereco atualizarEndereco(Long id, Endereco endereco) {
        Endereco enderecoExistente = enderecoRepository.findById(id).orElse(null);
        if (
            endereco != null &&
            enderecoExistente != null &&
            enderecoExistente.getId() != null &&
            endereco.getId() != null &&
            enderecoExistente.getId().intValue() == endereco.getId().intValue()
        ) {
            return enderecoRepository.save(endereco);
        } else {
            throw new RuntimeException();
        }
    }

}
