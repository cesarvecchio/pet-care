package br.com.petcare.dominio.enums;

import lombok.Getter;

@Getter
public enum GeneroEnum {
    FEMEA("Fêmea"),
    MACHO("Macho");

    private final String descricao;

    GeneroEnum(String descricao) {
        this.descricao = descricao;
    }

    public static GeneroEnum recuperarGenero(String especie) {
        for (GeneroEnum value : values()) {
            if(value.getDescricao().equals(especie)) return value;
        }
        return null;
    }
}
