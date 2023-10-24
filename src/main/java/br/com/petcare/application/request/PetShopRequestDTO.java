package br.com.petcare.application.request;

import br.com.petcare.domain.entity.Endereco;

import java.util.List;

public record PetShopRequestDTO(
        Integer id,
        String nome,
        String cpf,
        String cnpj,
        List<String> listaFuncionarios,
        Integer tipoServico,
        Endereco endereco
) {
}
