package br.com.petcare.aplicacao.controller;

import br.com.petcare.dominio.dto.PetDTO;
import br.com.petcare.dominio.servico.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<Page<PetDTO>> findAll(@PageableDefault(size = 10, page = 0, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(petService.findAll(pageable));
    }

    @PostMapping("/{idDono}")
    private ResponseEntity<PetDTO> criarPet(@PathVariable Integer idDono, @RequestBody PetDTO petDTO) {
        return ResponseEntity.ok(petService.criarPet(petDTO, idDono));
    }

}
