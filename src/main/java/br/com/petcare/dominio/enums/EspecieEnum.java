package br.com.petcare.dominio.enums;

import lombok.Getter;

@Getter
public enum EspecieEnum {
    GATO("Gato"),
    CACHORRO("Cachorro");

    private final String descricao;

    EspecieEnum(String descricao) {
        this.descricao = descricao;
    }

    public static EspecieEnum recuperarEspecie(String especie) {
        for (EspecieEnum value : values()) {
            if(value.getDescricao().equals(especie)) return value;
        }
        return null;
    }
}
