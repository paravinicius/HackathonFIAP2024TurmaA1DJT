package br.com.fiap.postech.hackathon2024.gestaoquarto.services;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Endereco;
import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Imovel;
import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Localidade;
import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.exceptions.QuartoNaoEncontradoException;
import br.com.fiap.postech.hackathon2024.gestaoquarto.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImovelService {
    @Autowired
    private final ImovelRepository imovelRepository;
    private final QuartoService quartoService;

    public ImovelService(ImovelRepository imovelRepository, QuartoService quartoService) {
        this.imovelRepository = imovelRepository;
        this.quartoService = quartoService;
    }

    public Imovel criarImovel(Imovel imovel) {
        Localidade localidade = imovel.getLocalidade();
        List<Quarto> quartosRecuperados = new ArrayList<>();

        if (localidade != null) {
            //TO DO
        }

        for (Quarto quarto : imovel.getQuartos()) {
            quarto = quartoService.buscarQuartoPorId(Long.valueOf(quarto.getId()));
            if (quarto == null || quarto.getId() == null)
                throw new QuartoNaoEncontradoException(0L);
            quartosRecuperados.add(quarto);
        }
        imovel.setQuartos(quartosRecuperados);

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
