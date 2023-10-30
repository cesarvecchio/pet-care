package br.com.petcare.application.controller;

import br.com.petcare.application.request.AgendamentoRequestDTO;
import br.com.petcare.application.request.BanhoTosaRequestDTO;
import br.com.petcare.application.response.BanhoTosaResponseDTO;
import br.com.petcare.domain.service.BanhoTosaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banho-tosa")
public class BanhoTosaController {

    private final BanhoTosaService banhoTosaService;

    public BanhoTosaController(BanhoTosaService banhoTosaService) {
        this.banhoTosaService = banhoTosaService;
    }

    @GetMapping
    public ResponseEntity<Page<BanhoTosaResponseDTO>> buscarTodos(@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable,
                                                                  @RequestParam(required = false) Integer idDono,
                                                                  @RequestParam(required = false) Integer idPet,
                                                                  @RequestParam(required = false) Integer idPetShop,
                                                                  @RequestParam(required = false) Integer tipoBanhoTosa) {
        return ResponseEntity.ok(banhoTosaService.buscarTodos(pageable, idDono, idPet, idPetShop, tipoBanhoTosa));
    }

    @PostMapping("{idPetShop}/{idPet}")
    public ResponseEntity<BanhoTosaResponseDTO> cadastrar(@PathVariable Integer idPetShop,
                                                          @PathVariable Integer idPet,
                                                          @RequestBody BanhoTosaRequestDTO banhoTosaRequestDTO) {
        return ResponseEntity.ok(banhoTosaService.cadastrar(idPetShop, idPet, banhoTosaRequestDTO));
    }

    @PostMapping("{idBanhoTosa}")
    public ResponseEntity<BanhoTosaResponseDTO> reagendar(@PathVariable Integer idBanhoTosa,
                                                          @RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {
        return ResponseEntity.ok(banhoTosaService.reagendar(idBanhoTosa, agendamentoRequestDTO));
    }

}
