package br.com.petcare.application.controller;

import br.com.petcare.application.request.AgendamentoRequestDTO;
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

    @PostMapping("{idDono}/{idPetShop}/{idPet}")
    public ResponseEntity<AgendamentoResponseDTO> cadastrar(@PathVariable Integer idDono,
                                                            @PathVariable Integer idPetShop,
                                                            @PathVariable Integer idPet,
                                                            @RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {
        return ResponseEntity.ok(agendamentoService.cadastrar(idDono, idPetShop, idPet, agendamentoRequestDTO));
    }

    @PutMapping("{idAgendamento}")
    public ResponseEntity<AgendamentoResponseDTO> atualizar(@PathVariable Integer idAgendamento,
                                                            @RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {
        return ResponseEntity.ok(agendamentoService.atualizar(idAgendamento, agendamentoRequestDTO));
    }
}
