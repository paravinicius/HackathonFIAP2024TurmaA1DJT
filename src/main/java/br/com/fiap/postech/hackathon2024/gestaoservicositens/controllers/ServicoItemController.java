package br.com.fiap.postech.hackathon2024.gestaoservicositens.controllers;

import br.com.fiap.postech.hackathon2024.gestaoservicositens.entities.ServicoItem;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.exceptions.ServicoItemNaoEncontradoException;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.services.ServicoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos-itens")
public class ServicoItemController {
    @Autowired
    private final ServicoItemService servicoItemService;

    public ServicoItemController(ServicoItemService servicoItemService) {
        this.servicoItemService = servicoItemService;
    }

    @PostMapping
    public ResponseEntity<ServicoItem> criarServicosItens(@RequestBody ServicoItem servicoItem) {
        ServicoItem novoServicoItem = servicoItemService.criarServicosItens(servicoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServicoItem);
    }

    @GetMapping
    public ResponseEntity<List<ServicoItem>> buscarTodosServicosItens() {
        List<ServicoItem> servicosItens = servicoItemService.buscarTodosServicosItens();
        return ResponseEntity.ok(servicosItens);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoItem> atualizarServicosItens(@PathVariable Long id, @RequestBody ServicoItem servicoItem) {
        try {
            ServicoItem servicoItemAtualizado = servicoItemService.atualizarServicosItens(id, servicoItem);
            if (servicoItemAtualizado != null) {
                return ResponseEntity.ok(servicoItemAtualizado);
            }
        }
        catch (RuntimeException e) {
            throw new ServicoItemNaoEncontradoException(id);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServicosItens(@PathVariable Long id) {
        try {
            servicoItemService.deletarServicosItens(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ServicoItemNaoEncontradoException(id);
        }
    }
}
