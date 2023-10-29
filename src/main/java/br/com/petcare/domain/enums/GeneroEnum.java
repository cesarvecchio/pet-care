package br.com.petcare.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum GeneroEnum {
    FEMEA(1, "Fêmea"),
    MACHO(2, "Macho");

    private final Integer id;
    private final String descricao;

    public static GeneroEnum recuperarGenero(String descricao) {
        return Arrays.stream(values()).filter(value -> value.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }

    public static GeneroEnum recuperarGenero(Integer id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
