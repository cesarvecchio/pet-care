package br.com.petcare.infra.repository;

import br.com.petcare.domain.entity.Agendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    Page<Agendamento> findAllByDonoId(Integer idDono, Pageable pageable);

    Page<Agendamento> findAllByPetId(Integer idPet, Pageable pageable);

    Page<Agendamento> findAllByPetShopId(Integer idPetShop, Pageable pageable);

}
