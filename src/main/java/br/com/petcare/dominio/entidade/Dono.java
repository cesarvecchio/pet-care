package br.com.petcare.dominio.entidade;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
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

    @OneToMany
    private List<Pet> listaPet;
}
