package br.com.fiap.postech.hackathon2024.gestaoquarto.repositories;

import br.com.fiap.postech.hackathon2024.gestaoquarto.entities.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {
}
