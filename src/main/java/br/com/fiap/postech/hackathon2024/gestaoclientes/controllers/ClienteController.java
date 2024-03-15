package br.com.fiap.postech.hackathon2024.gestaoclientes.controllers;

import br.com.fiap.postech.hackathon2024.gestaoclientes.entities.Cliente;
import br.com.fiap.postech.hackathon2024.gestaoclientes.services.ClienteService;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.exceptions.ServicoItemNaoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodosClientes() {
        List<Cliente> clientes = clienteService.buscarTodosOsClientes();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
            if (clienteAtualizado != null) {
                return ResponseEntity.ok(clienteAtualizado);
            }
        } catch (RuntimeException e) {
            throw new ServicoItemNaoEncontradoException(id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new ServicoItemNaoEncontradoException(id);
        }
    }
}
