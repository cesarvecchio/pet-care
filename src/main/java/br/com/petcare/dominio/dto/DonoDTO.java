package br.com.petcare.dominio.dto;

public record DonoDTO(
        Integer id,
        String nome,
        String cpf,
        String rg,
        String email,
        String senha
) {
}
