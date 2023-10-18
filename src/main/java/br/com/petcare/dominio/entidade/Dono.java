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

    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

    private Integer numero;

    @OneToMany(mappedBy = "dono")
    private List<Pet> listaPet;
}
