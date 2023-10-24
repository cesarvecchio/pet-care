package br.com.petcare.aplicacao.controller;

import br.com.petcare.dominio.dto.AgendamentoRequestDTO;
import br.com.petcare.dominio.dto.AgendamentoResponseDTO;
import br.com.petcare.dominio.servico.AgendamentoService;
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
}
