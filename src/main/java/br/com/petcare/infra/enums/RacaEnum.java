package br.com.petcare.infra.enums;

import lombok.Getter;

@Getter
public enum RacaEnum {
    PERSA("Persa"),
    SIAMES("Siamês"),
    MAINE_COON("Maine Coon"),
    ANGORA("Angorá"),
    SPHYNX("Sphynx"),
    RAGDOLL("Ragdoll"),
    ASHERA("Ashera"),
    AMERICAN_SHORTHAIR("American Shorthair"),

    SPITZ_ALEMAO("Spitz Alemão"),
    BULLDOG_FRANCES("Bulldog Francês"),
    SHIH_TZU("Shih Tzu"),
    ROTTWILER("Rottweiler"),
    PUG("Pug"),
    GOLDEN_RETRIVER("Golden Retriever"),
    PASTOR_ALEMAO("Pastor Alemão"),
    YORKSHIRE_TERRIER("Yorkshire Terrier"),

    CACHORRO_SRD("Desconhecido"),
    OUTRO("Outro");

    private final String descricao;

    RacaEnum(String descricao) {
        this.descricao = descricao;
    }


}
