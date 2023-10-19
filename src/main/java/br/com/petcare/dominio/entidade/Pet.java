package br.com.petcare.dominio.entidade;

import br.com.petcare.dominio.enums.EspecieEnum;
import br.com.petcare.dominio.enums.GeneroEnum;
import br.com.petcare.dominio.enums.HumorEnum;
import br.com.petcare.dominio.enums.RacaEnum;
import br.com.petcare.dominio.enums.conversor.EspecieEnumConversor;
import br.com.petcare.dominio.enums.conversor.GeneroEnumConversor;
import br.com.petcare.dominio.enums.conversor.HumorEnumConversor;
import br.com.petcare.dominio.enums.conversor.RacaEnumConversor;
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

    @Convert(converter = EspecieEnumConversor.class)
    private EspecieEnum especie;

    @Convert(converter = RacaEnumConversor.class)
    private RacaEnum raca;

    @Convert(converter = HumorEnumConversor.class)
    private HumorEnum humor;

    @Convert(converter = GeneroEnumConversor.class)
    private GeneroEnum genero;

    @ManyToOne
    private Dono dono;
}
