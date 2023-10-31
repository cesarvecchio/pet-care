package br.com.petcare.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@Getter
@AllArgsConstructor
public enum TipoBanhoTosaEnum {
    SOMENTE_BANHO(1, "Somente banho"),
    BANHO_TOSA(2, "Banho e tosa");

    private final Integer id;
    private final String descricao;

    public static TipoBanhoTosaEnum recuperarTipoBanhoTosa(String descricao) {
        return Arrays.stream(values()).filter(value -> value.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }

    public static TipoBanhoTosaEnum recuperarTipoBanhoTosa(Integer id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}