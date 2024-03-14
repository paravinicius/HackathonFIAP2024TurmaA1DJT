package br.com.fiap.postech.hackathon2024.gestaoreservas.controllers;

import br.com.fiap.postech.hackathon2024.gestaoreservas.entitites.Reserva;
import br.com.fiap.postech.hackathon2024.gestaoreservas.services.ReservaService;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.entities.ServicoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping ("/{reservaId}/itens-servicos")
    public ResponseEntity<List<ServicoItem>> buscarItensNaReserva(@PathVariable Long reservaId) {
            List<ServicoItem> resultados = reservaService.recuperaServicos(reservaId);
            return ResponseEntity.ok(resultados);
    }
}