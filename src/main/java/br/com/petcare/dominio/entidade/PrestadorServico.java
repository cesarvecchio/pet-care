package br.com.petcare.dominio.entidade;

import br.com.petcare.dominio.enums.TipoServicoEnum;
import br.com.petcare.dominio.enums.conversor.TipoServicoEnumConversor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "TB_PRESTADOR_SERVICO")
public class PrestadorServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = TipoServicoEnumConversor.class)
    private TipoServicoEnum tipoServico;
}
