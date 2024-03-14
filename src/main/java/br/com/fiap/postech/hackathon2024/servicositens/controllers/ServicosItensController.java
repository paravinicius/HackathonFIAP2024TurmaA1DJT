package br.com.fiap.postech.hackathon2024.servicositens.controllers;

import br.com.fiap.postech.hackathon2024.servicositens.entities.ServicoItem;
import br.com.fiap.postech.hackathon2024.servicositens.services.ServicosItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos-itens")
public class ServicosItensController {
    @Autowired
    private final ServicosItensService servicosItensService;

    public ServicosItensController(ServicosItensService servicosItensService) {
        this.servicosItensService = servicosItensService;
    }

    @PostMapping
    public ResponseEntity<ServicoItem> criarServicosItens(@RequestBody ServicoItem servicoItem) {
        ServicoItem novoServicoItem = servicosItensService.criarServicosItens(servicoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServicoItem);
    }

    @GetMapping
    public ResponseEntity<List<ServicoItem>> buscarTodosServicosItens() {
        List<ServicoItem> servicosItens = servicosItensService.buscarTodosServicosItens();
        return ResponseEntity.ok(servicosItens);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoItem> atualizarServicosItens(@PathVariable Long id, @RequestBody ServicoItem servicoItem) {
        ServicoItem servicoItemAtualizado = servicosItensService.atualizarServicosItens(id, servicoItem);
        if (servicoItemAtualizado != null) {
            return ResponseEntity.ok(servicoItemAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deletarServicosItens(@PathVariable Long id) {
        servicosItensService.deletarServicosItens(id);
        return ResponseEntity.noContent().build();
    }

}
