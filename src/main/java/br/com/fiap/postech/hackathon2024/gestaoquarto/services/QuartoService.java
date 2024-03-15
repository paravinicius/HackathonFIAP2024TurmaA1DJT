package br.com.fiap.postech.hackathon2024.gestaoquarto.services;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.repositories.QuartoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Quarto buscarQuartoPorId(Long id) {
        return quartoRepository.findById(id).orElse(null);
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

    @Transactional
    public void atualizarQuarto(Quarto quarto) {
        if (quarto == null || quarto.getId() == null) {
            throw new RuntimeException("Reserva inválida");
        }
        entityManager.merge(quarto);
    }

    public void adicionarDatasOcupadasNoQuarto(LocalDate dataInicio, LocalDate dataFim, Quarto quarto) {
        List<LocalDate> datasOcupadas = quarto.getDatasOcupadas();
        for (LocalDate data = dataInicio; !data.isAfter(dataFim); data = data.plusDays(1)) {
            datasOcupadas.add(data);
        }
        quarto.setDatasOcupadas(datasOcupadas);
    }

    public List<Quarto> buscarQuartosDisponiveis(int totalPessoas, LocalDate dataInicio, LocalDate dataFim) {
        List<Quarto> todosQuartos = quartoRepository.findAll();
        List<Quarto> quartosDisponiveis = new ArrayList<>();

        for (Quarto quarto : todosQuartos) {
            if (quarto.getCapacidadeMaximaPessoas() >= totalPessoas && !quarto.isOcupado(dataInicio, dataFim)) {
                quartosDisponiveis.add(quarto);
            }
        }

        return quartosDisponiveis;
    }
}
