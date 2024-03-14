package br.com.fiap.postech.hackathon2024.gestaoreservas.controllers;

import br.com.fiap.postech.hackathon2024.gestaoreservas.services.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservaService;


}
