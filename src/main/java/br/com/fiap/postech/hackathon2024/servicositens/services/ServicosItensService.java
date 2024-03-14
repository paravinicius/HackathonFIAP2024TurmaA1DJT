package br.com.fiap.postech.hackathon2024.servicositens.services;

import br.com.fiap.postech.hackathon2024.servicositens.entities.ServicosItens;
import br.com.fiap.postech.hackathon2024.servicositens.repositories.ServicosItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicosItensService {

    private final ServicosItensRepository servicosItensRepository;

    @Autowired
    public ServicosItensService(ServicosItensRepository servicosItensRepository) {
        this.servicosItensRepository = servicosItensRepository;
    }
    public ServicosItens criarServicosItens(ServicosItens servicosItens) {
        return servicosItensRepository.save(servicosItens);
    }

    public List<ServicosItens> buscarTodosServicosItens() {
        return servicosItensRepository.findAll();
    }

    public ServicosItens atualizarServicosItens(Long id, ServicosItens servicosItens) {
        ServicosItens servicosItensExistente = servicosItensRepository.findById(id).orElse(null);
        if (servicosItensExistente != null) {
            return servicosItensRepository.save(servicosItens);
        } else {
            return null;
        }
    }

    public void deletarServicosItens(Long id) {
        servicosItensRepository.deleteById(id);
    }
}
