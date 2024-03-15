package br.com.fiap.postech.hackathon2024.gestaoclientes.services;

import br.com.fiap.postech.hackathon2024.gestaoclientes.entities.Cliente;
import br.com.fiap.postech.hackathon2024.gestaoclientes.repositories.ClienteRepository;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.entities.ServicoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarTodosOsClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setCpf(cliente.getCpf());
            clienteExistente.setEmail(cliente.getEmail());
            clienteExistente.setDataNascimento(cliente.getDataNascimento());
            clienteExistente.setEnderecoPaisOrigem(cliente.getEnderecoPaisOrigem());
            clienteExistente.setNomeCompleto(cliente.getNomeCompleto());
            clienteExistente.setPaisDeOrigem(cliente.getPaisDeOrigem());
            clienteExistente.setTelefone(cliente.getTelefone());
            clienteExistente.setPassaporte(cliente.getPassaporte());
            return clienteRepository.save(clienteExistente);
        } else {
            throw new RuntimeException();
        }
    }
    public void deletarCliente(Long id) {
        Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente != null) {
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException();
        }
    }
}
