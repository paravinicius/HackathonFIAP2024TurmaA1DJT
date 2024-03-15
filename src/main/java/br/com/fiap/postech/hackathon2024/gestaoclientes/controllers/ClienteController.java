package br.com.fiap.postech.hackathon2024.gestaoclientes.controllers;

import br.com.fiap.postech.hackathon2024.gestaoclientes.entities.Cliente;
import br.com.fiap.postech.hackathon2024.gestaoclientes.repositories.ClienteRepository;
import br.com.fiap.postech.hackathon2024.gestaoclientes.controllers.dto.DadosCadastroCliente;
import br.com.fiap.postech.hackathon2024.gestaoclientes.services.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.json.JSONObject;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> cadastrar (@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder){
        JSONObject response = new JSONObject();
        var cliente = new Cliente(dados);
        var retorno = service.cadastrarCliente(cliente);
        response.put("message","cliente cadastrado com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }
}
