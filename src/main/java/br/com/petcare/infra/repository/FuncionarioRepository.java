package br.com.petcare.infra.repository;

import br.com.petcare.domain.entity.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByCpf(String cpf);

    Boolean existsByCpf(String cpf);

    Page<Funcionario> findAllByPetShopId(Integer idPetShop, Pageable pageable);
}
