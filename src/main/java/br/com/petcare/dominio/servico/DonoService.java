package br.com.petcare.dominio.servico;

import br.com.petcare.infra.repositorio.DonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonoService {
    private final DonoRepository donoRepository;

    @Autowired
    public DonoService(DonoRepository donoRepository) {
        this.donoRepository = donoRepository;
    }

}
