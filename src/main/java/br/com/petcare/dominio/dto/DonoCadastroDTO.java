package br.com.petcare.dominio.dto;

import br.com.petcare.dominio.entidade.Endereco;

public record DonoCadastroDTO(
        Integer id,
        String nome,
        String cpf,
        String rg,
        String email,
        String senha,

        Endereco endereco
) {
}
