package br.com.petcare.dominio.dto;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        LocalDateTime dataAgendamento,
        String observacao,
        String status,
        DonoDTO dono,
        PetShopRespostaDTO petShop,
        PetDTO pet
) {
}