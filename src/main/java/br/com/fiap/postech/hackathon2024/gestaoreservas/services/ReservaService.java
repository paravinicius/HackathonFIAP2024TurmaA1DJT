package br.com.fiap.postech.hackathon2024.gestaoreservas.services;

import br.com.fiap.postech.hackathon2024.gestaoreservas.entitites.Reserva;
import br.com.fiap.postech.hackathon2024.gestaoreservas.repositories.ReservaRepository;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.entities.ServicoItem;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.repositories.ServicoItemRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private final ReservaRepository reservaRepository;

    @Autowired
    private final EntityManager entityManager;

    @Autowired
    private final ServicoItemRepository servicoItemRepository;

    public ReservaService(ReservaRepository reservaRepository, EntityManager entityManager, ServicoItemRepository servicoItemRepository) {
        this.reservaRepository = reservaRepository;
        this.entityManager = entityManager;
        this.servicoItemRepository = servicoItemRepository;
    }

    public void adicionarItensServicosNaReserva(Long reservaId, List<Long> idsItensServicos) {
        Reserva reserva = entityManager.find(Reserva.class, reservaId);
        reserva.getItensServicos().addAll(idsItensServicos);
        reservaRepository.save(reserva);
    }

    public Reserva criarReserva() {
        Reserva reserva = new Reserva();
        return reservaRepository.save(reserva);
    }

    public List<Reserva> buscarTodasReservas() {
        return reservaRepository.findAll();
    }

    public List<ServicoItem> recuperaServicos(Long id) {
        Reserva reserva = entityManager.find(Reserva.class, id);
        List<Long> idsItensServicos = reserva.getItensServicos();
        List<ServicoItem> resultados = new ArrayList<>();
        for (Long idItem: idsItensServicos) {
            ServicoItem servicoItem = entityManager.find(ServicoItem.class, idItem);
            resultados.add(servicoItem);
        }
        return resultados;
    }
}
