package br.com.petcare.aplicacao.controller.excecao;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CpfException.class)
    public ResponseEntity<StandardError> cpfException(CpfException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError standardError = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Cpf Exception")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<StandardError> naoEncontradoException(NaoEncontradoException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError standardError = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Nao Encontrado Exception")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(PetNaoPertenceAoDonoException.class)
    public ResponseEntity<StandardError> petNaoPertenceDonoException(PetNaoPertenceAoDonoException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError standardError = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Pet Nao Pertence A Esse Dono")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(standardError);
    }
}
