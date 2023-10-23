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
    public ResponseEntity<PetDTO> cadastrar(@PathVariable Integer idDono, @RequestBody PetDTO petDTO) {
        return ResponseEntity.ok(petService.cadastrar(petDTO, idDono));
    }

    @PutMapping("/{idDono}/{idPet}")
    public ResponseEntity<PetDTO> atualizar(@PathVariable Integer idDono, @PathVariable Integer idPet,
                                               @RequestBody PetDTO petDTO) {
        return ResponseEntity.ok(petService.atualizar(idDono, idPet, petDTO));
    }

    @DeleteMapping("/{idDono}/{idPet}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idDono, @PathVariable Integer idPet) {
        this.petService.deletar(idDono, idPet);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dono/{idDono}")
    public ResponseEntity<Page<PetDTO>> consultarPetsPorDono(@PathVariable Integer idDono,
                                                          @PageableDefault(size = 10, page = 0, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(this.petService.consultarPetsPorDono(idDono, pageable));
    }

}
