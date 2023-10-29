package br.com.petcare.application.controller;

import br.com.petcare.application.request.DonoRequestDTO;
import br.com.petcare.application.response.DonoResponseDTO;
import br.com.petcare.domain.service.DonoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dono")
public class DonoController {

    private final DonoService donoService;

    public DonoController(DonoService donoService) {
        this.donoService = donoService;
    }

    @PostMapping
    public ResponseEntity<DonoResponseDTO> cadastrar(@RequestBody DonoRequestDTO donoDTO) {
        return ResponseEntity.ok(donoService.cadastrar(donoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonoResponseDTO> consultarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(donoService.toResponseDto(donoService.buscaPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonoResponseDTO> atualizar(@PathVariable Integer id, @RequestBody DonoRequestDTO donoRequestDTO) {
        return ResponseEntity.ok(donoService.atualizar(id, donoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        donoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}

