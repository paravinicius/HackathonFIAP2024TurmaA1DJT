package br.com.fiap.postech.hackathon2024.gestaoquarto.exceptions;

public class QuartoNaoEncontradoException extends RuntimeException {

    private Long id;
    public Long getId() {
        return id;
    }

    public QuartoNaoEncontradoException(Long id) {
        super("Quarto não encontrado com o ID " + id);
        this.id = id;
    }
}
