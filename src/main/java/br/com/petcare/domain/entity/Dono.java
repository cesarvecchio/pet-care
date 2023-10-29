package br.com.petcare.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "TB_DONO")
public class Dono extends Usuario {

    private String cpf;

    private String rg;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.REMOVE)
    private List<Pet> listaPet;
}
