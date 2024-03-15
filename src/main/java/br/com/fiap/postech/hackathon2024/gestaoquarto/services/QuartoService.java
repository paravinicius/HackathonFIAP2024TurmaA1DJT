package br.com.fiap.postech.hackathon2024.gestaoquarto.services;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.repositories.QuartoRepository;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.entities.ServicoItem;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartoService {
    @Autowired
    private final QuartoRepository quartoRepository;

    @Autowired
    private final EntityManager entityManager;

    public QuartoService(QuartoRepository quartoRepository, EntityManager entityManager) {
        this.quartoRepository = quartoRepository;
        this.entityManager = entityManager;
    }

    public Quarto criarQuarto(Quarto quarto){
        return quartoRepository.save(quarto);
    }

    public List<Quarto> buscarTodosQuartos() {
        return quartoRepository.findAll();
    }

    public Quarto atualizarQuarto(Long id, Quarto quarto) {
        Quarto quartoExistente = quartoRepository.findById(id).orElse(null);
        if (quartoExistente != null) {
            quartoExistente.setValorDiaria(quarto.getValorDiaria());
            quartoExistente.setCapacidadeMaximaPessoas(quarto.getCapacidadeMaximaPessoas());
            quartoExistente.setDatasOcupadas(quarto.getDatasOcupadas());
            return quartoRepository.save(quartoExistente);
        } else {
            throw new RuntimeException();
        }
    }
    public void deletarQuarto(Long id) {
        Quarto quartoExistente = quartoRepository.findById(id).orElse(null);
        if (quartoExistente != null) {
            quartoRepository.deleteById(id);
        } else {
            throw new RuntimeException();
        }
    }
}
