package br.com.petcare.application.controller;

import br.com.petcare.application.request.FuncionarioRequestDTO;
import br.com.petcare.application.response.FuncionarioResponseDTO;
import br.com.petcare.domain.service.FuncionarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/{idPetShop}")
    public ResponseEntity<FuncionarioResponseDTO> cadastrar(@PathVariable Integer idPetShop, @RequestBody FuncionarioRequestDTO funcionarioDTO) {
        return ResponseEntity.ok(funcionarioService.cadastrar(idPetShop, funcionarioDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> consultarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(funcionarioService.toResponseDTO(funcionarioService.buscaPorId(id)));
    }

    @GetMapping("/pet-shop/{idPetShop}")
    public ResponseEntity<Page<FuncionarioResponseDTO>> consultarFuncionariosPorPetShop(@PathVariable Integer idPetShop,
                                                                                        @PageableDefault(size = 10, page = 0, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(funcionarioService.consultarFuncionariosPorPetShop(idPetShop, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable Integer id, @RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        return ResponseEntity.ok(funcionarioService.atualizar(id, funcionarioRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        funcionarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}

