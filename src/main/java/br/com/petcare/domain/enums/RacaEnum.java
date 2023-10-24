package br.com.petcare.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RacaEnum {
    CACHORRO_SRD(1, "Desconhecido"),
    OUTRO(2, "Outro"),

    PERSA(3, "Persa"),
    SIAMES(4, "Siamês"),
    MAINE_COON(5, "Maine Coon"),
    ANGORA(6, "Angorá"),
    SPHYNX(7, "Sphynx"),
    RAGDOLL(8, "Ragdoll"),
    ASHERA(9, "Ashera"),
    AMERICAN_SHORTHAIR(10, "American Shorthair"),

    SPITZ_ALEMAO(11, "Spitz Alemão"),
    BULLDOG_FRANCES(12, "Bulldog Francês"),
    SHIH_TZU(13, "Shih Tzu"),
    ROTTWILER(14, "Rottweiler"),
    PUG(15, "Pug"),
    GOLDEN_RETRIVER(16, "Golden Retriever"),
    PASTOR_ALEMAO(17, "Pastor Alemão"),
    YORKSHIRE_TERRIER(18, "Yorkshire Terrier");

    private final Integer id;
    private final String descricao;


    public static RacaEnum recuperarRaca(String descricao) {
        for (RacaEnum value : values()) {
            if(value.getDescricao().equals(descricao)) return value;
        }
        return null;
    }

    public static RacaEnum recuperarRaca(Integer id) {
        for (RacaEnum value : values()) {
            if(value.getId().equals(id)) return value;
        }
        return null;
    }

}
