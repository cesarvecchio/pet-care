package br.com.petcare.dominio.dto;

import java.time.LocalDate;

public record AgendamentoDTO(
        LocalDate dataAgendamento,
        String observacao,
        String status,
        DonoDTO dono,
        PetShopDTO petShop,
        PetDTO pet
) {
}