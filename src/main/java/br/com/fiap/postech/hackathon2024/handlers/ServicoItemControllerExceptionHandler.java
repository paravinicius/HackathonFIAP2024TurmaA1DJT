package br.com.fiap.postech.hackathon2024.handlers;

import br.com.fiap.postech.hackathon2024.gestaoservicositens.exceptions.ServicoItemNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServicoItemControllerExceptionHandler {

    @ExceptionHandler(ServicoItemNaoEncontradoException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ServicoItemNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}