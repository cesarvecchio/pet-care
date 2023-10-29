package br.com.petcare.application.controller;

import br.com.petcare.application.request.PetRequestDTO;
import br.com.petcare.application.response.PetResponseDTO;
import br.com.petcare.domain.service.PetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<Page<PetResponseDTO>> findAll(@PageableDefault(size = 10, page = 0, sort = "nome") Pageable pageable,
                                                        @RequestParam(required = false) String nome,
                                                        @RequestParam(required = false) LocalDate dataNascimento,
                                                        @RequestParam(required = false) Double peso,
                                                        @RequestParam(required = false) Double tamanho,
                                                        @RequestParam(required = false) Integer especie,
                                                        @RequestParam(required = false) Integer raca,
                                                        @RequestParam(required = false) Integer genero,
                                                        @RequestParam(required = false) Integer humor) {
        return ResponseEntity.ok(petService.findAll(pageable, nome, dataNascimento, peso, tamanho, especie, raca, genero, humor));
    }

    @PostMapping("/{idDono}")
    public ResponseEntity<PetResponseDTO> cadastrar(@PathVariable Integer idDono, @RequestBody PetRequestDTO petDTO) {
        return ResponseEntity.ok(petService.cadastrar(petDTO, idDono));
    }

    @PutMapping("/{idDono}/{idPet}")
    public ResponseEntity<PetResponseDTO> atualizar(@PathVariable Integer idDono, @PathVariable Integer idPet,
                                                    @RequestBody PetRequestDTO petDTO) {
        return ResponseEntity.ok(petService.atualizar(idDono, idPet, petDTO));
    }

    @DeleteMapping("/{idDono}/{idPet}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idDono, @PathVariable Integer idPet) {
        petService.deletar(idDono, idPet);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dono/{idDono}")
    public ResponseEntity<Page<PetResponseDTO>> consultarPetsPorDono(@PathVariable Integer idDono,
                                                                     @PageableDefault(size = 10, page = 0, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(petService.consultarPetsPorDono(idDono, pageable));
    }

}
