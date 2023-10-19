package br.com.petcare.infra.excecao;

public class DonoNaoEncontradoException extends RuntimeException{
    public DonoNaoEncontradoException(String message) {
        super(message);
    }

    public DonoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
