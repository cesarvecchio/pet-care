package br.com.petcare.application.response;

import br.com.petcare.domain.valueObject.Endereco;

public record DonoResponseDTO(
        Integer id,
        String nome,
        String cpf,
        String rg,
        Endereco endereco
) {
}
