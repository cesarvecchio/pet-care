package br.com.petcare.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Embeddable
public class Endereco {

    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

    private String numero;

    private Double latitude;

    private Double longitude;
}
