package com.ufms.olx.controllers;

import com.ufms.olx.domain.dto.PessoaDTO.PessoaDTO;
import com.ufms.olx.domain.entities.Pessoa;
import com.ufms.olx.services.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/pessoa")
public class PessoaController implements GenericController<Pessoa, PessoaDTO> {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> addEntity(@RequestBody PessoaDTO dto) {
        Pessoa pessoa = pessoaService.insert(dto);
        return ResponseEntity.ok().body(pessoa);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getEntityById(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaService.getById(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEntity(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.ok().body("");
    }

    @GetMapping
    public ResponseEntity<?> getPessoas() {
        var pessoas = pessoaService.getAll();
        return ResponseEntity.ok().body(pessoas);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntity(Pessoa pessoa, Long id) {
        Pessoa updatedPessoa = pessoaService.update(pessoa, id);
        return ResponseEntity.ok().body(updatedPessoa);
    }

    @Override
    public ResponseEntity<?> getAllEntities() {
        return ResponseEntity.ok().body(pessoaService.getAll());
    }
}
