package br.com.petcare.application.controller;

import br.com.petcare.application.request.FuncionarioRequestDTO;
import br.com.petcare.application.response.FuncionarioResponseDTO;
import br.com.petcare.domain.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> cadastrar(@RequestBody FuncionarioRequestDTO funcionarioDTO) {
        return ResponseEntity.ok(funcionarioService.cadastrar(funcionarioDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> consultarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(this.funcionarioService.toResponseDTO(funcionarioService.buscaPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable Integer id, @RequestBody FuncionarioResponseDTO funcionarioResponseDTO) {
        return ResponseEntity.ok(funcionarioService.atualizar(id, funcionarioResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        this.funcionarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}

