package br.com.fiap.postech.hackathon2024.gestaoservicositens.exceptions;

public class ServicoItemNaoEncontradoException extends RuntimeException {
    public ServicoItemNaoEncontradoException(Long id) {
        super("Serviço/item não encontrado com o ID " + id);
    }
}
