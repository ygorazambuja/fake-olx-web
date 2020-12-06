package com.ufms.olx.controllers;

import com.ufms.olx.domain.dto.PessoaDTO.PessoaJuridicaDTO;
import com.ufms.olx.domain.entities.PessoaJuridica;
import com.ufms.olx.services.PessoaJuricaService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/pessoaJuridica")
public class PessoaJuridicaController {
    final PessoaJuricaService pessoaJuridicaService;

    public PessoaJuridicaController(PessoaJuricaService pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @PostMapping
    public ResponseEntity<?> insere(@RequestBody @Valid PessoaJuridicaDTO dto) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaService.insert(dto);
        return ResponseEntity.ok().body(pessoaJuridica);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable("id") Long id) {
        PessoaJuridica pessoa = pessoaJuridicaService.getById(id);
        return ResponseEntity.ok().body(pessoa);
    }
}
