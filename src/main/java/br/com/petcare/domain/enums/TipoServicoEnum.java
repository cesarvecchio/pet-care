package br.com.petcare.domain.enums;

import lombok.Getter;

import java.util.Arrays;

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
        return Arrays.stream(values()).filter(value -> value.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }

    public static TipoServicoEnum recuperarServico(Integer id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
