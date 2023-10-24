package br.com.petcare.application.controller;

import br.com.petcare.application.request.PetShopRequestDTO;
import br.com.petcare.application.response.PetShopResponseDTO;
import br.com.petcare.domain.service.PetShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet-shop")
public class PetShopController {
    private final PetShopService petShopService;

    @Autowired
    public PetShopController(PetShopService petShopService) {
        this.petShopService = petShopService;
    }

    @GetMapping
    public ResponseEntity<Page<PetShopResponseDTO>> buscarTodos(@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(petShopService.buscarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<PetShopResponseDTO> cadastrar(@RequestBody PetShopRequestDTO petShopRequestDTO) {
        return ResponseEntity.ok(petShopService.cadastrar(petShopRequestDTO));
    }

    @PutMapping("{idPetShop}")
    public ResponseEntity<PetShopResponseDTO> atualizar(@PathVariable Integer idPetShop,
                                                        @RequestBody PetShopRequestDTO petShopRequestDTO) {
        return ResponseEntity.ok(petShopService.atualizar(idPetShop, petShopRequestDTO));
    }
}
