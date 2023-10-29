package br.com.petcare.domain.entity;

import br.com.petcare.domain.enums.EspecieEnum;
import br.com.petcare.domain.enums.GeneroEnum;
import br.com.petcare.domain.enums.HumorEnum;
import br.com.petcare.domain.enums.RacaEnum;
import br.com.petcare.domain.enums.converter.EspecieEnumConverter;
import br.com.petcare.domain.enums.converter.GeneroEnumConverter;
import br.com.petcare.domain.enums.converter.HumorEnumConverter;
import br.com.petcare.domain.enums.converter.RacaEnumConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "TB_PET")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private LocalDate dataNascimento;

    private Double peso;

    private Double tamanho;

    @Convert(converter = EspecieEnumConverter.class)
    private EspecieEnum especie;

    @Convert(converter = RacaEnumConverter.class)
    private RacaEnum raca;

    @Convert(converter = HumorEnumConverter.class)
    private HumorEnum humor;

    @Convert(converter = GeneroEnumConverter.class)
    private GeneroEnum genero;

    @ManyToOne
    private Dono dono;
}
