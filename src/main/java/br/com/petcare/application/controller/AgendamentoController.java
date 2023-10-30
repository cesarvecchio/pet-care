package br.com.petcare.application.controller;

import br.com.petcare.application.response.AgendamentoResponseDTO;
import br.com.petcare.domain.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PutMapping("{idAgendamento}")
    public ResponseEntity<AgendamentoResponseDTO> atualizarStatus(@PathVariable Integer idAgendamento,
                                                                  @RequestParam(required = true) Integer status) {
        return ResponseEntity.ok(agendamentoService.atualizarStatus(idAgendamento, status));
    }

}
