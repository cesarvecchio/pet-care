package br.com.petcare.dominio.dto;

import br.com.petcare.dominio.entidade.Endereco;

import java.util.List;

public record PetShopRequisicaoDTO(
        Integer id,
        String nome,
        String cpf,
        String cnpj,
        List<String> listaFuncionarios,
        Integer tipoServico,
        Endereco endereco
) {
}
