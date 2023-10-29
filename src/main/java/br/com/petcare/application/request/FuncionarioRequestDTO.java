package br.com.petcare.application.request;

public record FuncionarioRequestDTO(
        Integer id,
        String nome,
        String cpf,
        String rg

) {
}