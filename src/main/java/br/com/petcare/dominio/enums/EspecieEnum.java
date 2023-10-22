package br.com.petcare.dominio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EspecieEnum {
    GATO(1, "Gato"),
    CACHORRO(2, "Cachorro");

    private final Integer id;
    private final String descricao;

    public static EspecieEnum recuperarEspecie(String descricao) {
        for (EspecieEnum value : values()) {
            if(value.getDescricao().equals(descricao)) return value;
        }
        return null;
    }

    public static EspecieEnum recuperarEspecie(Integer id) {
        for (EspecieEnum value : values()) {
            if(value.getId().equals(id)) return value;
        }
        return null;
    }
}
