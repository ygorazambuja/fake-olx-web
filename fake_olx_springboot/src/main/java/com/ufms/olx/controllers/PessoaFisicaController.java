package com.ufms.olx.controllers;

import com.ufms.olx.domain.dto.PessoaDTO.PessoaFisicaDTO;
import com.ufms.olx.domain.entities.PessoaFisica;
import com.ufms.olx.services.PessoaFisicaService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/pessoaFisica")
public class PessoaFisicaController {
    final PessoaFisicaService pessoaFisicaService;

    public PessoaFisicaController(PessoaFisicaService pessoaFisicaService) {
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> inserePessoaFisica(@RequestBody @Valid PessoaFisicaDTO dto) {
        return ResponseEntity.ok().body(pessoaFisicaService.insert(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable("id") Long id) {
        PessoaFisica pessoa = pessoaFisicaService.getById(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping
    public ResponseEntity<?> buscaTodos() {
        return ResponseEntity.ok().body(pessoaFisicaService.getAll());
    }
}
