package br.com.petcare.application.request;

import br.com.petcare.domain.valueObject.Endereco;

import java.util.List;

public record PetShopRequestDTO(
        Integer id,
        String nome,
        String email,
        String senha,
        String cnpj,
        List<FuncionarioRequestDTO> funcionarios,
        Integer tipoServico,
        Endereco endereco
) {
}
