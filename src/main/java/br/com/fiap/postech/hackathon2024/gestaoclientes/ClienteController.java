package br.com.fiap.postech.hackathon2024.gestaoclientes;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder){
        JSONObject response = new JSONObject();

        var cliente = new Cliente(dados);
        repository.save(cliente);
        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        response.put("message","cliente cadastrado com sucesso");

        return ResponseEntity.status(HttpStatus.OK).body(response.toString());
    }
}
