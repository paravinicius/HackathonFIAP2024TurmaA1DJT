package br.com.fiap.postech.hackathon2024.gestaoquarto.exceptions;

public class QuartoNaoEncontradoException extends RuntimeException {

    public QuartoNaoEncontradoException(Long id) {
        super("Quarto não encontrado com o ID " + id);
    }
}
