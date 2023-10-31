package br.com.petcare.application.response;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Integer id,
        LocalDateTime dataAgendamento,
        String status,
        DonoResponseDTO dono,
        PetShopResponseDTO petShop,
        PetResponseDTO pet
) {
}