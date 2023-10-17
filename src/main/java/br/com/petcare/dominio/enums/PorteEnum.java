package br.com.petcare.dominio.enums;

import lombok.Getter;

@Getter
public enum PorteEnum {
    MINI("Mini"),
    PEQUENO("Pequeno"),
    MEDIO("Médio"),
    GRANDE("Grande"),
    GIGANTE("Gigante");

    private final String descricao;

    PorteEnum(String descricao) {
        this.descricao = descricao;
    }

    public static PorteEnum recuperarPorte(String raca) {
        for (PorteEnum value : values()) {
            if(value.getDescricao().equals(raca)) return value;
        }
        return null;
    }
}
