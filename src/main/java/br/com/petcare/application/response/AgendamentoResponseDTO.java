package br.com.petcare.application.response;

import br.com.petcare.application.request.PetRequestDTO;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        LocalDateTime dataAgendamento,
        String observacao,
        String status,
        DonoResponseDTO dono,
        PetShopResponseDTO petShop,
        PetRequestDTO pet
) {
}