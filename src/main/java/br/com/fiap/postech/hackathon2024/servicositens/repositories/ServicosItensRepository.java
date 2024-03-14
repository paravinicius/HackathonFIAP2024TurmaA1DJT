package br.com.fiap.postech.hackathon2024.servicositens.repositories;

import br.com.fiap.postech.hackathon2024.servicositens.entities.ServicoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicosItensRepository extends JpaRepository<ServicoItem, Long> {
}
