package br.com.fiap.postech.hackathon2024.gestaoreservas.services;

import br.com.fiap.postech.hackathon2024.gestaoclientes.entities.Cliente;
import br.com.fiap.postech.hackathon2024.gestaoclientes.services.ClienteService;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private final ReservaRepository reservaRepository;

    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final QuartoService quartoService;
    @Autowired
    private final EntityManager entityManager;


    public ReservaService(ReservaRepository reservaRepository, EntityManager entityManager, QuartoService quartoService, ClienteService clienteService) {
        this.reservaRepository = reservaRepository;
        this.entityManager = entityManager;
        this.quartoService = quartoService;
        this.clienteService = clienteService;
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

    public String gerarTextoFinalReserva(Long reservaId) {
        Reserva reserva = this.buscarReservaPorId(reservaId).orElse(null);
        List<Quarto> quartos = this.recuperaQuartosReserva(reservaId);
        List<ServicoItem> servicosItens = this.recuperaItensServicosPorReserva(reservaId);
        String textoQuartos = this.construirMensagemQuartos(quartos);
        var totalDias = this.calcularTotalDias(reserva);
        String totaldeDias = "Estes quartos foram alugados por um total de " + totalDias + " dias.";
        String textoServicosItens = this.construirMensagemServicos(servicosItens);
        var precoTotalQuartos = this.calcularCustoDosQuartosDaReserva(reserva);
        String textoPrecoQuartos = "O custo total dos quartos para esta reserva é de: R$" + precoTotalQuartos;
        var precoTotalServicos = this.calcularCustoDosServicosItensDaReserva(servicosItens);
        String textoPrecoServicos = "O custo total dos itens e serviços para esta reserva é de: R$" + precoTotalServicos;
        var precoFinal = precoTotalQuartos.add(precoTotalServicos);
        String finalizar = "Somando tudo, o valor da reserva é de R$" + precoFinal + ". \n\nReserva finalizada!";
        StringBuilder sb = new StringBuilder();
        sb.append(textoQuartos + "\n");
        sb.append(totaldeDias + "\n");
        sb.append(textoServicosItens + "\n");
        sb.append(textoPrecoQuartos + "\n");
        sb.append(textoPrecoServicos + "\n");
        sb.append(finalizar);
        return sb.toString();
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

    public Long calcularTotalDias(Reserva reserva) {
        var dataInicio = reserva.getDataInicio();
        var dataFim = reserva.getDataFim();
        return ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;
    }

    public BigDecimal calcularCustoDosQuartosDaReserva(Reserva reserva) {
        LocalDate dataInicio = reserva.getDataInicio();
        LocalDate dataFim = reserva.getDataFim();
        long totalDias = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;

        List<Long> idsQuartos = reserva.getQuartos();
        List<Quarto> quartos = new ArrayList<>();
        for (Long idQuarto : idsQuartos) {
            Quarto quarto = quartoService.buscarQuartoPorId(idQuarto);
            if (quarto != null) {
                quartos.add(quarto);
            }
        }
        double totalGeral = 0.0;
        for (Quarto quarto : quartos) {
            double totalQuarto = quarto.getValorDiaria().doubleValue() * totalDias;
            totalGeral += totalQuarto;
        }
        return BigDecimal.valueOf(totalGeral);
    }

    public Reserva criarReserva(Long clienteId) {
        Cliente cliente = clienteService.buscarClientePorId(clienteId);
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
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

    public String construirMensagemServicos(List<ServicoItem> servicos) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Na sua reserva, estão incluídos os seguintes itens e serviços: ");
        for (ServicoItem servico : servicos) {
            mensagem.append(servico.getNome()).append(", ");
        }
        mensagem.deleteCharAt(mensagem.length() - 2);
        return mensagem.toString();
    }

    public String construirMensagemQuartos(List<Quarto> quartos) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Na sua reserva, foram incluídos ").append(quartos.size()).append(" quartos, dos tipos: ");
        for (Quarto quarto : quartos) {
            mensagem.append(quarto.getTipoQuarto()).append(", ");
        }
        mensagem.deleteCharAt(mensagem.length() - 2);

        return mensagem.toString();
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
