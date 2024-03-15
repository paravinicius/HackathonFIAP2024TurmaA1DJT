package br.com.fiap.postech.hackathon2024.gestaoquarto.controllers;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Endereco;
import br.com.fiap.postech.hackathon2024.gestaoquarto.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
