package br.com.fiap.postech.hackathon2024.gestaoclientes.services;

import br.com.fiap.postech.hackathon2024.gestaoclientes.entities.Cliente;
import br.com.fiap.postech.hackathon2024.gestaoclientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
