package br.com.fiap.postech.hackathon2024.gestaoreservas.services;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.services.QuartoService;
import br.com.fiap.postech.hackathon2024.gestaoreservas.entitites.Reserva;
import br.com.fiap.postech.hackathon2024.gestaoreservas.repositories.ReservaRepository;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.entities.ServicoItem;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private final ReservaRepository reservaRepository;

    @Autowired
    private final QuartoService quartoService;
    @Autowired
    private final EntityManager entityManager;

    public ReservaService(ReservaRepository reservaRepository, EntityManager entityManager, QuartoService quartoService) {
        this.reservaRepository = reservaRepository;
        this.entityManager = entityManager;
        this.quartoService = quartoService;
    }

    public void adicionarItensServicosNaReserva(Long reservaId, List<Long> idsItensServicos) {
        Reserva reserva = entityManager.find(Reserva.class, reservaId);
        reserva.getItensServicos().addAll(idsItensServicos);
        reservaRepository.save(reserva);
    }

    public void adicionarQuartosNaReserva(Long reservaId, List<Long> idsQuartos) {
        Reserva reserva = entityManager.find(Reserva.class, reservaId);
        reserva.getQuartos().addAll(idsQuartos);
        reservaRepository.save(reserva);
    }

    public List<Quarto> recuperaQuartosReserva(Long id) {
        Reserva reserva = entityManager.find(Reserva.class, id);
        List<Long> idsQuartos = reserva.getQuartos();
        List<Quarto> resultados = new ArrayList<>();
        for (Long idItem: idsQuartos) {
            Quarto quarto = entityManager.find(Quarto.class, idItem);
            resultados.add(quarto);
        }
        return resultados;
    }

    public BigDecimal calcularCustoDosQuartosDaReserva(List<Quarto> lista) {
        return lista.stream()
                .map(Quarto::getValorDiaria)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Reserva criarReserva() {
        Reserva reserva = new Reserva();
        return reservaRepository.save(reserva);
    }

    public List<Reserva> buscarTodasReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<ServicoItem> recuperaItensServicosPorReserva(Long id) {
        Reserva reserva = entityManager.find(Reserva.class, id);
        List<Long> idsItensServicos = reserva.getItensServicos();
        List<ServicoItem> resultados = new ArrayList<>();
        for (Long idItem: idsItensServicos) {
            ServicoItem servicoItem = entityManager.find(ServicoItem.class, idItem);
            resultados.add(servicoItem);
        }
        return resultados;
    }

    public BigDecimal calcularCustoDosServicosItensDaReserva(List<ServicoItem> lista) {
        return lista.stream()
                .map(ServicoItem::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    @Transactional
    public void adicionarDatasNaReserva(Reserva reserva, LocalDate dataInicio, LocalDate dataFim) {
        if (reserva == null || reserva.getId() == null) {
            throw new RuntimeException("Reserva inválida");
        }
        if (!isValidDate(dataInicio.toString()) || !isValidDate(dataFim.toString())) {
            throw new RuntimeException("Datas inválidas");
        }
        reserva.setDataInicio(dataInicio);
        reserva.setDataFim(dataFim);
        entityManager.merge(reserva);

        for (Long quartoId: reserva.getQuartos()) {
            Quarto quarto = quartoService.buscarQuartoPorId(quartoId);
            quartoService.adicionarDatasOcupadasNoQuarto(dataInicio, dataFim, quarto);
            quartoService.atualizarQuarto(quarto);
        }
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
