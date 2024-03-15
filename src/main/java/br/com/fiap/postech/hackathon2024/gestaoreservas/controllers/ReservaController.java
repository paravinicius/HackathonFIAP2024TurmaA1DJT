package br.com.fiap.postech.hackathon2024.gestaoreservas.controllers;

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
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

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
    public ResponseEntity<Reserva> criarReserva() {
        Reserva novaReserva = reservaService.criarReserva();
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
    }

    @PostMapping("/{reservaId}/itens-servicos")
    public ResponseEntity<?> adicionarItensNaReserva(@PathVariable Long reservaId, @RequestBody List<Long> idsItensServicos) {
        try {
            reservaService.adicionarItensServicosNaReserva(reservaId, idsItensServicos);
            Integer size = idsItensServicos.size();
            return ResponseEntity.ok(size + " itens adicionados com sucesso na reserva");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar itens e serviços na reserva: " + e.getMessage());
        }
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
        if (!isValidDate(dataInicio) || !isValidDate(dataFim)) {
            return ResponseEntity.badRequest().body("Datas inválidas");
        }
        reserva.setDataInicio(LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        reserva.setDataFim(LocalDate.parse(dataFim, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        reservaService.atualizarReserva(reserva);
        return ResponseEntity.ok(reserva);
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}