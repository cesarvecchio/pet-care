package br.com.petcare.application.request;

import br.com.petcare.domain.valueObject.Endereco;

public record DonoRequestDTO(
        Integer id,
        String nome,
        String cpf,
        String rg,
        String email,
        String senha,
        Endereco endereco
) {
}
