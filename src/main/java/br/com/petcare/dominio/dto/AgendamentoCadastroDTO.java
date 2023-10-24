package br.com.petcare.dominio.dto;

import java.time.LocalDate;

public record AgendamentoCadastroDTO(
        LocalDate dataAgendamento,
        String observacao,
        Integer status
) {
}
