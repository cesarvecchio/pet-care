package br.com.petcare.domain.entity;


import br.com.petcare.domain.enums.StatusEnum;
import br.com.petcare.domain.enums.converter.StatusEnumConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "TB_AGENDAMENTO")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataAgendamento;

    private String observacao;

    @Convert(converter = StatusEnumConverter.class)
    private StatusEnum status;

    @ManyToOne
    private Dono dono;

    @ManyToOne
    private PetShop petShop;

    @ManyToOne
    private Pet pet;

}
