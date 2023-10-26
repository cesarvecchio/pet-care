package br.com.petcare.application.response;

public record FuncionarioResponseDTO(
        Integer id,
        String nome,
        String cpf,
        String rg
) {
}
