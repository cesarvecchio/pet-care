package br.com.petcare.dominio.enums;

import lombok.Getter;

@Getter
public enum HumorEnum {
    DOCIL("Dócil"),
    BRINCALHAO("Brincalhão"),
    AGRESSIVO("Agressivo");

    private final String descricao;

    HumorEnum(String descricao) {
        this.descricao = descricao;
    }

    public static HumorEnum recuperarHumor(String raca) {
        for (HumorEnum value : values()) {
            if(value.getDescricao().equals(raca)) return value;
        }
        return null;
    }
}
