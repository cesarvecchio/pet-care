package br.com.petcare.domain.entity;

import br.com.petcare.domain.enums.TipoServicoEnum;
import br.com.petcare.domain.enums.converter.TipoServicoEnumConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = TipoServicoEnumConverter.class)
    private TipoServicoEnum tipoServico;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Agendamento agendamento;

}
