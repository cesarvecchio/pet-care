package br.com.petcare.infra.repositorio;

import br.com.petcare.dominio.entidade.Dono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonoRepository extends JpaRepository<Dono, Integer> {
}
