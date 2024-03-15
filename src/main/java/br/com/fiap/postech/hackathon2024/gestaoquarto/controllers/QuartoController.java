package br.com.fiap.postech.hackathon2024.gestaoquarto.controllers;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.exceptions.QuartoNaoEncontradoException;
import br.com.fiap.postech.hackathon2024.gestaoquarto.services.QuartoService;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.exceptions.ServicoItemNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    private final QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @PostMapping
    public ResponseEntity<Quarto> criarQuarto(@RequestBody Quarto quartoRequest) {
        Quarto quarto = new Quarto();
        quarto.setTiposCamas(quartoRequest.getTiposCamas());
        quarto.setTipoQuarto(quartoRequest.getTipoQuarto());
        quarto.setValorDiaria(quartoRequest.getValorDiaria());
        quarto.setCapacidadeMaximaPessoas(quartoRequest.getCapacidadeMaximaPessoas());
        quarto.setIsDisponivelParaNovaReserva(quartoRequest.getIsDisponivelParaNovaReserva());
        quarto.setComodidades(quartoRequest.getComodidades());
        Quarto novoQuarto = quartoService.criarQuarto(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoQuarto);
    }

    @GetMapping
    public ResponseEntity<List<Quarto>> buscarTodosQuartos() {
        List<Quarto> quartos = quartoService.buscarTodosQuartos();
        return ResponseEntity.ok(quartos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> atualizarQuarto(@PathVariable Long id, @RequestBody Quarto quarto) {
        try {
            Quarto quartoAtualizado = quartoService.atualizarQuarto(id, quarto);
            if (quartoAtualizado != null) {
                return ResponseEntity.ok(quartoAtualizado);
            }
        }
        catch (RuntimeException e) {
            throw new ServicoItemNaoEncontradoException(id);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/bloquear")
    public ResponseEntity<Quarto> bloquearQuartoParaReserva(@PathVariable Long id) {
        Quarto quartoAtualizado = quartoService.bloquearQuarto(id);
        return ResponseEntity.ok(quartoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuarto(@PathVariable Long id) {
        try {
            quartoService.deletarQuarto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new QuartoNaoEncontradoException(id);
        }
    }

}
