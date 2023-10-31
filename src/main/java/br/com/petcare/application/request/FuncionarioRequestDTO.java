package br.com.petcare.application.request;

public record FuncionarioRequestDTO(
        String nome,
        String cpf,
        String rg

) {
}