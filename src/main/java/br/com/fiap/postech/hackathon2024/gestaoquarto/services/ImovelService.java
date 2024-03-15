package br.com.fiap.postech.hackathon2024.gestaoquarto.services;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Endereco;
import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Imovel;
import br.com.fiap.postech.hackathon2024.gestaoquarto.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelService {
    @Autowired
    private final ImovelRepository imovelRepository;

    public ImovelService(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public Imovel criarImovel(Imovel imovel) {
        return imovelRepository.save(imovel);
    }

    public List<Imovel> buscarTodosImoveis() {
        return imovelRepository.findAll();
    }

    public Imovel buscarImovelPorId(Long id) {
        return imovelRepository.findById(id).orElse(null);
    }

    public Imovel atualizarImovel(Long id, Imovel imovel) {
        Imovel imovelExistente = imovelRepository.findById(id).orElse(null);
        if (
                imovel != null &&
                        imovelExistente != null &&
                        imovelExistente.getId() != null &&
                        imovel.getId() != null &&
                        imovelExistente.getId().intValue() == imovel.getId().intValue()
        ) {
            return imovelRepository.save(imovel);
        } else {
            throw new RuntimeException();
        }
    }
}
