package br.com.petcare.infra.excecao;

public class CpfException extends RuntimeException{
    public CpfException(String message) {
        super(message);
    }

    public CpfException(String message, Throwable cause) {
        super(message, cause);
    }
}
