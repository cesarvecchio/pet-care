package br.com.petcare.dominio.dto;

import br.com.petcare.dominio.entidade.Endereco;

import java.util.List;

public record PetShopRespostaDTO(
        Integer id,
        String nome,
        String cpf,
        String cnpj,
        List<String> listaFuncionarios,
        String tipoServico,
        Endereco endereco
) {
}
