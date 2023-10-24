package br.com.petcare.application.response;

public record DonoResponseDTO(
        Integer id,
        String nome,
        String cpf,
        String rg
) {
}
