package br.com.fiap.postech.hackathon2024.servicositens.controllers;

import br.com.fiap.postech.hackathon2024.servicositens.entities.ServicosItens;
import br.com.fiap.postech.hackathon2024.servicositens.services.ServicosItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos-itens")
public class ServicosItensController {

    private final ServicosItensService servicosItensService;

    public ServicosItensController(ServicosItensService servicosItensService) {
        this.servicosItensService = servicosItensService;
    }

    @PostMapping
    public ResponseEntity<ServicosItens> criarServicosItens(@RequestBody ServicosItens servicosItens) {
        ServicosItens novoServicosItens = servicosItensService.criarServicosItens(servicosItens);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServicosItens);
    }

    @GetMapping
    public ResponseEntity<List<ServicosItens>> buscarTodosServicosItens() {
        List<ServicosItens> servicosItens = servicosItensService.buscarTodosServicosItens();
        return ResponseEntity.ok(servicosItens);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicosItens> atualizarServicosItens(@PathVariable Long id, @RequestBody ServicosItens servicosItens) {
        ServicosItens servicosItensAtualizado = servicosItensService.atualizarServicosItens(id, servicosItens);
        if (servicosItensAtualizado != null) {
            return ResponseEntity.ok(servicosItensAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deletarServicosItens(@PathVariable Long id) {
        servicosItensService.deletarServicosItens(id);
        return ResponseEntity.noContent().build();
    }

}
