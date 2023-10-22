package br.com.petcare.aplicacao.controller;

import br.com.petcare.dominio.dto.PrestadorServicoDTO;
import br.com.petcare.dominio.entidade.PrestadorServico;
import br.com.petcare.dominio.servico.PrestadorServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prestador-servico")
public class PrestadorServicoController {
    private final PrestadorServicoService prestadorServicoService;

    @Autowired
    public PrestadorServicoController(PrestadorServicoService prestadorServicoService) {
        this.prestadorServicoService = prestadorServicoService;
    }

    @GetMapping
    public ResponseEntity<Page<PrestadorServicoDTO>> buscarTodos(@PageableDefault(size = 10, page = 0, sort = "id")Pageable pageable) {
        return ResponseEntity.ok(prestadorServicoService.buscarTodos(pageable));
    }
}
