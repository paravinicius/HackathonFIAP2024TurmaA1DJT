package br.com.fiap.postech.hackathon2024.gestaoquarto.controllers;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Endereco;
import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.services.EnderecoService;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.exceptions.ServicoItemNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco enderecoRequest) {
        Endereco enderecoNovo = enderecoService.criarQuarto(enderecoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoNovo);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> buscarTodosEnderecos() {
        List<Endereco> endereco = enderecoService.buscarTodosEnderecos();
        return ResponseEntity.ok(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        try {
            Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, endereco);
            if (enderecoAtualizado != null) {
                return ResponseEntity.ok(enderecoAtualizado);
            }
        }
        catch (RuntimeException e) {
            throw new ServicoItemNaoEncontradoException(id);
        }
        return ResponseEntity.notFound().build();
    }

}
