package br.com.petcare.dominio.entidade;

import br.com.petcare.infra.enums.EspecieEnum;
import br.com.petcare.infra.enums.RacaEnum;
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

    @Enumerated(EnumType.STRING)
    private EspecieEnum especie;

    @Enumerated
    private RacaEnum raca;

    @ManyToOne
    private Dono dono;
}
