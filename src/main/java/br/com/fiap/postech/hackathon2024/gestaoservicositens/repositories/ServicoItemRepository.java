package br.com.fiap.postech.hackathon2024.gestaoservicositens.repositories;

import br.com.fiap.postech.hackathon2024.gestaoservicositens.entities.ServicoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoItemRepository extends JpaRepository<ServicoItem, Long> {

    public ServicoItem getById(Long id);
}
