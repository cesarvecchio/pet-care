package br.com.petcare.dominio.dto;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        LocalDateTime dataAgendamento,
        String observacao,
        Integer status,
        DonoCadastroDTO dono,
        PetShopRequisicaoDTO petShop,
        PetDTO pet
) {
}
