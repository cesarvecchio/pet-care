package br.com.petcare.infra.repositorio;

import br.com.petcare.dominio.entidade.Dono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonoRepository extends JpaRepository<Dono, Integer> {

    Optional<Dono> findByCpf(String cpf);

    Boolean existsByCpf(String cpf);

    Optional<Dono> findByEmailAndSenha(String email, String senha);
}
