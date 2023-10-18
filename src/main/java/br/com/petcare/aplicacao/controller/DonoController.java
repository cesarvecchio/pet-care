package br.com.petcare.aplicacao.controller;

import br.com.petcare.dominio.dto.DonoDTO;
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
    public ResponseEntity<DonoDTO> cadastrar(@RequestBody DonoDTO donoDTO) {
        return ResponseEntity.ok(donoService.cadastrar(donoDTO));
    }

}

