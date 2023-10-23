package br.com.petcare.dominio.dto;

import java.util.List;

public record PetShopDTO(
        Integer id,
        String nome,
        String cpf,
        String cnpj,
        List<String> listaFuncionarios,
        String tipoServico
) {
}
