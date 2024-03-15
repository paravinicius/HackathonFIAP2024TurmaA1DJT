package br.com.fiap.postech.hackathon2024.gestaoquarto.repositories;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Endereco;
import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
