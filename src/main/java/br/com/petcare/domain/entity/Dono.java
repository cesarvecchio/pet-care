package br.com.petcare.domain.entity;

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
@Table(name = "TB_DONO")
public class Dono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cpf;

    private String rg;

    private String email;

    private String senha;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.REMOVE)
    private List<Pet> listaPet;
}
