package br.com.petcare.infra.repositorio;

import br.com.petcare.dominio.entidade.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Integer> {
    boolean existsPetByIdAndDonoId(Integer id, Integer idDono);

    Page<Pet> findAllByDonoId(Integer idDono, Pageable pageable);
}
