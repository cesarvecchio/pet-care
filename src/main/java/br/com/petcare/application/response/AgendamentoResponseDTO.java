package br.com.petcare.application.response;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        LocalDateTime dataAgendamento,
        String observacao,
        String status,
        DonoResponseDTO dono,
        PetShopResponseDTO petShop,
        PetResponseDTO pet
) {
}