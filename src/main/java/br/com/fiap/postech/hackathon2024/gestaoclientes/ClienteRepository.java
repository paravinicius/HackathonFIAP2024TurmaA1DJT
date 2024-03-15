package br.com.fiap.postech.hackathon2024.gestaoclientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente getReferenceById(PathVariable id);
}
