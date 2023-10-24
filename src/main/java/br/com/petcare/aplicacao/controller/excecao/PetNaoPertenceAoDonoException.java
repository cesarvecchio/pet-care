package br.com.petcare.aplicacao.controller.excecao;

public class PetNaoPertenceAoDonoException extends RuntimeException{
    public PetNaoPertenceAoDonoException(String message) {
        super(message);
    }

    public PetNaoPertenceAoDonoException(String message, Throwable cause) {
        super(message, cause);
    }
}
