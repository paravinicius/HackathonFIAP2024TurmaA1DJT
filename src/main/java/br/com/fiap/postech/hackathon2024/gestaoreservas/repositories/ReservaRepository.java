package br.com.fiap.postech.hackathon2024.gestaoreservas.repositories;

import br.com.fiap.postech.hackathon2024.gestaoreservas.entitites.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
