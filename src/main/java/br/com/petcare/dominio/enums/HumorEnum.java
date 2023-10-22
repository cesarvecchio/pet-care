package br.com.petcare.dominio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HumorEnum {
    DOCIL(1, "Dócil"),
    BRINCALHAO(2, "Brincalhão"),
    AGRESSIVO(3, "Agressivo");

    private final Integer id;
    private final String descricao;

    public static HumorEnum recuperarHumor(String descricao) {
        for (HumorEnum value : values()) {
            if(value.getDescricao().equals(descricao)) return value;
        }
        return null;
    }

    public static HumorEnum recuperarHumor(Integer id) {
        for (HumorEnum value : values()) {
            if(value.getId().equals(id)) return value;
        }
        return null;
    }
}
