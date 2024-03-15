package br.com.fiap.postech.hackathon2024.gestaoclientes.repositories;

import br.com.fiap.postech.hackathon2024.gestaoclientes.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
