package br.com.petcare.application.request;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        LocalDateTime dataAgendamento,
        Integer status,
        DonoRequestDTO dono,
        PetShopRequestDTO petShop,
        PetRequestDTO pet
) {
}
