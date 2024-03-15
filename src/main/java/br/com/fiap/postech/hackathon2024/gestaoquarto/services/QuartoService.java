package br.com.fiap.postech.hackathon2024.gestaoquarto.services;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import br.com.fiap.postech.hackathon2024.gestaoquarto.repositories.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartoService {
    @Autowired
    private final QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    public Quarto criarQuaro(Quarto quarto){
        return quartoRepository.save(quarto);
    }
}
