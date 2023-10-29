package br.com.petcare.infra.repository;

import br.com.petcare.domain.entity.PetShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetShopRepository extends JpaRepository<PetShop, Integer> {
}
