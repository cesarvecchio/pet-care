package br.com.petcare.infra.repositorio;

import br.com.petcare.dominio.entidade.PrestadorServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestadorServicoRepository extends JpaRepository<PrestadorServico, Integer> {
}
