package br.com.petcare.dominio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GeneroEnum {
    FEMEA(1, "Fêmea"),
    MACHO(2, "Macho");

    private final Integer id;
    private final String descricao;

    public static GeneroEnum recuperarGenero(String descricao) {
        for (GeneroEnum value : values()) {
            if(value.getDescricao().equals(descricao)) return value;
        }
        return null;
    }

    public static GeneroEnum recuperarGenero(Integer id) {
        for (GeneroEnum value : values()) {
            if(value.getId().equals(id)) return value;
        }
        return null;
    }
}
