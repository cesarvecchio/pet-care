package br.com.petcare.infra.repository;

import br.com.petcare.domain.entity.BanhoTosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanhoTosaRepository extends JpaRepository<BanhoTosa, Integer> {
}
