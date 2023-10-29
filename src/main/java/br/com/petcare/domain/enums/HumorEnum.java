package br.com.petcare.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum HumorEnum {
    DOCIL(1, "Dócil"),
    BRINCALHAO(2, "Brincalhão"),
    AGRESSIVO(3, "Agressivo");

    private final Integer id;
    private final String descricao;

    public static HumorEnum recuperarHumor(String descricao) {
        return Arrays.stream(values()).filter(value -> value.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }

    public static HumorEnum recuperarHumor(Integer id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
