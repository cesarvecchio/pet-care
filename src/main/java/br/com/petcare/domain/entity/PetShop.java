package br.com.petcare.domain.entity;

import br.com.petcare.domain.enums.TipoServicoEnum;
import br.com.petcare.domain.enums.converter.TipoServicoEnumConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "TB_PET_SHOP")
public class PetShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cpf;

    private String cnpj;

    @Embedded
    private Endereco endereco;

    @ElementCollection
    private List<String> listaFuncionarios;

    @Convert(converter = TipoServicoEnumConverter.class)
    private TipoServicoEnum tipoServico;
}
