package br.com.petcare.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum EspecieEnum {
    GATO(1, "Gato"),
    CACHORRO(2, "Cachorro");

    private final Integer id;
    private final String descricao;

    public static EspecieEnum recuperarEspecie(String descricao) {
        return Arrays.stream(values()).filter(value -> value.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }

    public static EspecieEnum recuperarEspecie(Integer id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
