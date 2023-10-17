package br.com.petcare.infra.enums;

import lombok.Getter;

@Getter
public enum EspecieEnum {
    GATO("Gato"),
    CACHORRO("Cachorro");

    private final String descricao;

    EspecieEnum(String descricao) {
        this.descricao = descricao;
    }
}
