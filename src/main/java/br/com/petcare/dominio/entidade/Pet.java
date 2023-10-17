package br.com.petcare.dominio.entidade;

import br.com.petcare.dominio.enums.EspecieEnum;
import br.com.petcare.dominio.enums.HumorEnum;
import br.com.petcare.dominio.enums.PorteEnum;
import br.com.petcare.dominio.enums.RacaEnum;
import br.com.petcare.dominio.enums.conversor.EspecieEnumConversor;
import br.com.petcare.dominio.enums.conversor.HumorEnumConversor;
import br.com.petcare.dominio.enums.conversor.PorteEnumConversor;
import br.com.petcare.dominio.enums.conversor.RacaEnumConversor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "TB_PET")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Convert(converter = EspecieEnumConversor.class)
    private EspecieEnum especie;

    @Convert(converter = RacaEnumConversor.class)
    private RacaEnum raca;

    @Convert(converter = PorteEnumConversor.class)
    private PorteEnum porte;

    @Convert(converter = HumorEnumConversor.class)
    private HumorEnum humor;

    @ManyToOne
    private Dono dono;
}
