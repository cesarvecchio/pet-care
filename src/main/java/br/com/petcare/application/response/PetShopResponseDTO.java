package br.com.petcare.application.response;

import br.com.petcare.domain.valueObject.Endereco;

import java.util.List;

public record PetShopResponseDTO(
        Integer id,
        String nome,
        String cnpj,
        List<FuncionarioResponseDTO> funcionarios,
        String tipoServico,
        Endereco endereco
) {
}
