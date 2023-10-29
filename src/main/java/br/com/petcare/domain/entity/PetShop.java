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
public class PetShop extends Usuario {

    private String cnpj;

    @OneToMany(mappedBy = "petShop", cascade = CascadeType.REMOVE)
    private List<Funcionario> funcionarios;

    @Convert(converter = TipoServicoEnumConverter.class)
    private TipoServicoEnum tipoServico;
}
