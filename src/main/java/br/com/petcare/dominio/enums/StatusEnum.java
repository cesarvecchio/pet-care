package br.com.petcare.dominio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    AGENDADO(1, "Agendado"),
    CANCELADO(2, "Cancelado"),
    EM_ANDAMENTO(3, "Em Andamento"),
    FINALIZADO(4, "Finalizado"),
    REAGENDADO(5, "Reagendado");

    private final Integer id;
    private final String descricao;

    public static StatusEnum recuperarStatus(String descricao) {
        return Arrays.stream(values()).filter(value -> value.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }

    public static StatusEnum recuperarStatus(Integer id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}


