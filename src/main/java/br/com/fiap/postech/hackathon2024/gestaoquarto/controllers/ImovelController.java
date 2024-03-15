package br.com.fiap.postech.hackathon2024.gestaoquarto.controllers;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Imovel;
import br.com.fiap.postech.hackathon2024.gestaoquarto.services.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    @Autowired
    private ImovelService imovelService;

    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @PostMapping
    public ResponseEntity<Imovel> criarEndereco(@RequestBody Imovel imovelRequest) {
        Imovel imovelNovo = imovelService.criarImovel(imovelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(imovelNovo);
    }
}
