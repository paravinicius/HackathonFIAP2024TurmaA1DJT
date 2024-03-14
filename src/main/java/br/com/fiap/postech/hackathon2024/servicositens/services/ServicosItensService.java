package br.com.fiap.postech.hackathon2024.servicositens.services;

import br.com.fiap.postech.hackathon2024.servicositens.entities.ServicoItem;
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

    public ServicoItem criarServicosItens(ServicoItem servicoItem) {
        return servicosItensRepository.save(servicoItem);
    }

    public List<ServicoItem> buscarTodosServicosItens() {
        return servicosItensRepository.findAll();
    }

    public ServicoItem atualizarServicosItens(Long id, ServicoItem servicoItem) {
        ServicoItem servicoItemExistente = servicosItensRepository.findById(id).orElse(null);
        if (servicoItemExistente != null) {
            return servicosItensRepository.save(servicoItem);
        } else {
            return null;
        }
    }

    public void deletarServicosItens(Long id) {
        servicosItensRepository.deleteById(id);
    }
}
