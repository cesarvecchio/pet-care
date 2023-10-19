package br.com.petcare.aplicacao.controller;

import br.com.petcare.dominio.dto.DonoCadastroDTO;
import br.com.petcare.dominio.dto.DonoDTO;
import br.com.petcare.dominio.entidade.Dono;
import br.com.petcare.dominio.servico.DonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dono")
public class DonoController {

    private final DonoService donoService;

    @Autowired
    public DonoController(DonoService donoService) {
        this.donoService = donoService;
    }

    @PostMapping
    public ResponseEntity<DonoDTO> cadastrar(@RequestBody DonoCadastroDTO donoDTO) {
        return ResponseEntity.ok(donoService.cadastrar(donoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonoDTO> consultarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(this.donoService.toDto(donoService.buscaPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonoDTO> atualizar(@PathVariable Integer id, @RequestBody DonoDTO donoDTO) {
        return ResponseEntity.ok(donoService.atualizar(id, donoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        this.donoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}

