package br.com.fiap.postech.hackathon2024.gestaoquarto.controllers;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.services.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos-quartos")
public class QuartoController {

    @Autowired
    private final QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @PostMapping
    public ResponseEntity<Quarto> criarQuarto(@RequestBody Quarto quarto) {
        Quarto novoQuarto = quartoService.criarQuaro(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoQuarto);
    }

}
