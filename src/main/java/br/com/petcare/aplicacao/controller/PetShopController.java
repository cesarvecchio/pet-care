package br.com.petcare.aplicacao.controller;

import br.com.petcare.dominio.dto.PetShopDTO;
import br.com.petcare.dominio.servico.PetShopService;
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
    public ResponseEntity<Page<PetShopDTO>> buscarTodos(@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(petShopService.buscarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<PetShopDTO> cadastrar(@RequestBody PetShopDTO petShopDTO) {
        return ResponseEntity.ok(petShopService.cadastrar(petShopDTO));
    }
}
