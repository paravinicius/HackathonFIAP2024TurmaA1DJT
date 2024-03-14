package br.com.fiap.postech.hackathon2024.gestaoreservas.services;

import br.com.fiap.postech.hackathon2024.gestaoreservas.entitites.Reserva;
import br.com.fiap.postech.hackathon2024.gestaoreservas.repositories.ReservaRepository;
import br.com.fiap.postech.hackathon2024.servicositens.entities.ServicoItem;
import br.com.fiap.postech.hackathon2024.servicositens.repositories.ServicosItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservasService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ServicosItensRepository servicosItensRepository;

    public void adicionarItensServicosNaReserva(Long reservaId, List<Long> idsItensServicos) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada com o ID: " + reservaId));


        List<ServicoItem> itensServicos = servicosItensRepository.findAllById(idsItensServicos);

        reserva.getItensServicos().addAll(itensServicos);
        reservaRepository.save(reserva);
    }
}
