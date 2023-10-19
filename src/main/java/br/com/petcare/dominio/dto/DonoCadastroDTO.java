package br.com.petcare.dominio.dto;

public record DonoCadastroDTO(
        Integer id,
        String nome,
        String cpf,
        String rg,
        String email,
        String senha
) {
}
