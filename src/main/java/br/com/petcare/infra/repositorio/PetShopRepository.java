package br.com.petcare.infra.repositorio;

import br.com.petcare.dominio.entidade.PetShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetShopRepository extends JpaRepository<PetShop, Integer> {
}
