package br.com.petcare.application.controller;

import br.com.petcare.application.request.AgendamentoRequestDTO;
import br.com.petcare.application.response.AgendamentoResponseDTO;
import br.com.petcare.domain.service.AgendamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public ResponseEntity<Page<AgendamentoResponseDTO>> buscarTodos(@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable,
                                                                    @RequestParam(required = false) Integer idDono,
                                                                    @RequestParam(required = false) Integer idPet,
                                                                    @RequestParam(required = false) Integer idPetShop) {
        return ResponseEntity.ok(agendamentoService.buscarTodos(pageable, idDono, idPet, idPetShop));
    }

    @PostMapping("{idPetShop}/{idPet}")
    public ResponseEntity<AgendamentoResponseDTO> cadastrar(@PathVariable Integer idPetShop,
                                                            @PathVariable Integer idPet,
                                                            @RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {
        return ResponseEntity.ok(agendamentoService.cadastrar(idPetShop, idPet, agendamentoRequestDTO));
    }

    @PutMapping("{idAgendamento}")
    public ResponseEntity<AgendamentoResponseDTO> atualizar(@PathVariable Integer idAgendamento,
                                                            @RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {
        return ResponseEntity.ok(agendamentoService.atualizar(idAgendamento, agendamentoRequestDTO));
    }
}
