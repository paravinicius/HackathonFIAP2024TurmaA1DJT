package br.com.fiap.postech.hackathon2024.gestaoreservas.controllers;

import br.com.fiap.postech.hackathon2024.emailsender.EmailSenderService;
import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.services.QuartoService;
import br.com.fiap.postech.hackathon2024.gestaoreservas.entitites.Reserva;
import br.com.fiap.postech.hackathon2024.gestaoreservas.services.ReservaService;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.entities.ServicoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public ResponseEntity<List<Reserva>> buscarTodasReservas() {
        List<Reserva> reservas = reservaService.buscarTodasReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{reservaId}")
    public ResponseEntity<?> buscarReservaPorId(@PathVariable Long reservaId) {
        var reserva = reservaService.buscarReservaPorId(reservaId);
        if (reserva.isPresent()) {
            return ResponseEntity.ok(reserva.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> criarReserva(@RequestParam(name = "clienteId", required = false) Long clienteId) {
        if (clienteId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não está logado");
        }
        Reserva novaReserva = reservaService.criarReserva(clienteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
    }

    @PutMapping("/{reservaId}/itens-servicos")
    public ResponseEntity<?> adicionarItensNaReserva(@PathVariable Long reservaId, @RequestBody List<Long> idsItensServicos) {
        try {
            reservaService.adicionarItensServicosNaReserva(reservaId, idsItensServicos);
            Integer size = idsItensServicos.size();
            return ResponseEntity.ok(size + " itens adicionados com sucesso na reserva");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar itens e serviços na reserva: " + e.getMessage());
        }
    }

    @PutMapping("/{reservaId}/quartos")
    public ResponseEntity<?> adicionarQuartosNaReserva(@PathVariable Long reservaId, @RequestBody List<Long> idsItensServicos) {
        try {
            reservaService.adicionarQuartosNaReserva(reservaId, idsItensServicos);
            Integer size = idsItensServicos.size();
            return ResponseEntity.ok(size + " quartos adicionados com sucesso na reserva");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar quartos na reserva: " + e.getMessage());
        }
    }

    @GetMapping("/{reservaId}/finalizar")
    public ResponseEntity<String> finalizarReserva(@PathVariable Long reservaId) {
        Reserva reserva = reservaService.buscarReservaPorId(reservaId).orElse(null);
        String textoFinal = reservaService.gerarTextoFinalReserva(reservaId);
        emailSenderService.sendEmail(reserva.getCliente().getEmail(), "Reserva finalizada!", textoFinal);
        System.out.println("E-mail sent!");
        return ResponseEntity.ok().body(textoFinal);
    }

    @GetMapping ("/{reservaId}/quartos/calcular-total")
    public ResponseEntity<String> calcularPrecoQuartosReserva(@PathVariable Long reservaId) {
        Reserva reserva = reservaService.buscarReservaPorId(reservaId).orElse(null);
        var total = reservaService.calcularCustoDosQuartosDaReserva(reserva);
        return ResponseEntity.ok("O custo total dos quartos para esta reserva é de: R$" + total);
    }

    @GetMapping ("/{reservaId}/quartos")
    public ResponseEntity<List<Quarto>> buscarQuartosNaReserva(@PathVariable Long reservaId) {
        List<Quarto> resultados = reservaService.recuperaQuartosReserva(reservaId);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping ("/{reservaId}/itens-servicos/calcular-total")
    public ResponseEntity<String> calcularPrecoServicosItensReserva(@PathVariable Long reservaId) {
        List<ServicoItem> resultados = reservaService.recuperaItensServicosPorReserva(reservaId);
        var total = reservaService.calcularCustoDosServicosItensDaReserva(resultados);
        return ResponseEntity.ok("O custo total dos itens e serviços para esta reserva é de: R$" + total);
    }

    @GetMapping ("/{reservaId}/itens-servicos")
    public ResponseEntity<List<ServicoItem>> buscarItensNaReserva(@PathVariable Long reservaId) {
            List<ServicoItem> resultados = reservaService.recuperaItensServicosPorReserva(reservaId);
            return ResponseEntity.ok(resultados);
    }

    @PutMapping("/{id}/datas")
    public ResponseEntity<?> adicionarDatasReserva(@PathVariable Long id,
                                                   @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") String dataInicio,
                                                   @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") String dataFim) {
        var reserva = reservaService.buscarReservaPorId(id).orElse(null);
        var dataInicioLocalDate = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        var dataFimLocalDate = LocalDate.parse(dataFim, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        reservaService.adicionarDatasNaReserva(reserva, dataInicioLocalDate, dataFimLocalDate);
        return ResponseEntity.ok("Datas de início e final adicionadas com sucesso na reserva");
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Quarto>> pesquisarQuartosDisponiveis(
            @RequestParam("totalPessoas") int totalPessoas,
            @RequestParam("dataInicio") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataFim) {

        List<Quarto> quartosDisponiveis = quartoService.buscarQuartosDisponiveis(totalPessoas, dataInicio, dataFim);
        return ResponseEntity.ok(quartosDisponiveis);
    }


}