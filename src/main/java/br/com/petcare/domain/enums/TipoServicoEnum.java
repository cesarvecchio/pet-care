package br.com.petcare.domain.enums;

import lombok.Getter;

@Getter
public enum TipoServicoEnum {
    BANHO_TOSA(1, "Banho e tosa"),
    PASSEIO(2, "Passeio"),
    VETERINARIO(3, "Veterinário"),
    ADESTRAMENTO(4, "Adestramento"),
    HOSPEDAGEM(5, "Hospedagem");


    private final Integer id;
    private final String descricao;

    TipoServicoEnum(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static TipoServicoEnum recuperarServico(String descricao) {
        for (TipoServicoEnum value : values()) {
            if(value.getDescricao().equals(descricao)) return value;
        }
        return null;
    }

    public static TipoServicoEnum recuperarServico(Integer id) {
        for (TipoServicoEnum value : values()) {
            if(value.getId().equals(id)) return value;
        }
        return null;
    }
}
