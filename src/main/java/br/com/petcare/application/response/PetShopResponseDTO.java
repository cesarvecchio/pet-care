package br.com.petcare.application.response;

import br.com.petcare.domain.entity.Endereco;

import java.util.List;

public record PetShopResponseDTO(
        Integer id,
        String nome,
        String cpf,
        String cnpj,
        List<String> listaFuncionarios,
        String tipoServico,
        Endereco endereco
) {
}
