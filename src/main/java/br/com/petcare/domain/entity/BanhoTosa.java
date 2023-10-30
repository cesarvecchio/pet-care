package br.com.petcare.domain.entity;

import br.com.petcare.domain.enums.TipoBanhoTosaEnum;
import br.com.petcare.domain.enums.converter.TipoBanhoTosaConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "TB_BANHO_TOSA")
public class BanhoTosa extends Servico {

    private String servicosAdicionais;
    private String cuidadosEspeciais;

    @Convert(converter = TipoBanhoTosaConverter.class)
    private TipoBanhoTosaEnum tipoBanhoTosa;

    @OneToOne
    private Funcionario funcionarioResponsavel;

}
