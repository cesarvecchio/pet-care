package br.com.petcare.infra.excecao;

public class NaoEncontradoException extends RuntimeException{
    public NaoEncontradoException(String message) {
        super(message);
    }

    public NaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
