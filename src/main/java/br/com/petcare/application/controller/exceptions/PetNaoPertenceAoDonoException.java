package br.com.petcare.application.controller.exceptions;

public class PetNaoPertenceAoDonoException extends RuntimeException {
    public PetNaoPertenceAoDonoException(String message) {
        super(message);
    }

    public PetNaoPertenceAoDonoException(String message, Throwable cause) {
        super(message, cause);
    }
}
