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
    public ResponseEntity<Page<PetShopResponseDTO>> buscarTodos(@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable,
                                                                @RequestParam(required = false) String nome,
                                                                @RequestParam(required = false) String cpf,
                                                                @RequestParam(required = false) String cnpj,
                                                                @RequestParam(required = false) Integer tipoServico) {
        return ResponseEntity.ok(petShopService.buscarTodos(pageable, nome, cpf, cnpj, tipoServico));
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

    @DeleteMapping("{idPetShop}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idPetShop) {
        petShopService.deletar(idPetShop);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idPetShop}")
    public ResponseEntity<PetShopResponseDTO> consultarPorId(@PathVariable Integer idPetShop) {
        return ResponseEntity.ok(petShopService.toDTOResposta(petShopService.buscarPorId(idPetShop)));
    }
}
