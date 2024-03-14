package br.com.fiap.postech.hackathon2024.gestaoservicositens.services;

import br.com.fiap.postech.hackathon2024.gestaoservicositens.entities.ServicoItem;
import br.com.fiap.postech.hackathon2024.gestaoservicositens.repositories.ServicoItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoItemService {
    @Autowired
    private final ServicoItemRepository servicoItemRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public ServicoItemService(ServicoItemRepository servicoItemRepository) {
        this.servicoItemRepository = servicoItemRepository;
    }

    public ServicoItem criarServicosItens(ServicoItem servicoItem) {
        return servicoItemRepository.save(servicoItem);
    }

    public List<ServicoItem> buscarTodosServicosItens() {
        return servicoItemRepository.findAll();
    }

    public ServicoItem atualizarServicosItens(Long id, ServicoItem servicoItem) {
        ServicoItem servicoItemExistente = servicoItemRepository.findById(id).orElse(null);
        if (servicoItemExistente != null) {
            servicoItemExistente.setValor(servicoItem.getValor());
            servicoItemExistente.setNome(servicoItem.getNome());
            return servicoItemRepository.save(servicoItemExistente);
        } else {
            throw new RuntimeException();
        }
    }

    public void deletarServicosItens(Long id) {
        ServicoItem servicoItemExistente = servicoItemRepository.findById(id).orElse(null);
        if (servicoItemExistente != null) {
            servicoItemRepository.deleteById(id);
        } else {
            throw new RuntimeException();
        }
    }

}
