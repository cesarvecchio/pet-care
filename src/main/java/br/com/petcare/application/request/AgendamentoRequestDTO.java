package br.com.petcare.application.request;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        LocalDateTime dataAgendamento,
        String observacao,
        Integer status,
        DonoRequestDTO dono,
        PetShopRequestDTO petShop,
        PetRequestDTO pet
) {
}
