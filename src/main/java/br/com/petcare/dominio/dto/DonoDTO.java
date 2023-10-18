package br.com.petcare.dominio.dto;

public record DonoDTO(
        Integer id,
        String nome,
        String cpf,
        String rg,
        String email,
        String senha,
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        Integer numero
) {
}
