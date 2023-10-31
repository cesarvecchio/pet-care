package br.com.petcare.application.response;

import br.com.petcare.domain.valueObject.Endereco;

public record PetShopResponseDTO(
        Integer id,
        String nome,
        String cnpj,
        String tipoServico,
        Endereco endereco
) {
}
