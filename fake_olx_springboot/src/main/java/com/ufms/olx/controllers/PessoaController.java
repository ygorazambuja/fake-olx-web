package com.ufms.olx.controllers;

import com.ufms.olx.domain.dto.PessoaDTO.GetPessoaFiltroDto;
import com.ufms.olx.domain.dto.PessoaDTO.PessoaDTO;
import com.ufms.olx.domain.entities.Pessoa;
import com.ufms.olx.domain.enums.SituacaoPessoa;
import com.ufms.olx.domain.enums.TipoPessoa;
import com.ufms.olx.services.PessoaService;
import com.ufms.olx.services.lambda.GetPessoaPorFiltro;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/pessoa")
public class PessoaController implements GenericController<Pessoa, PessoaDTO> {
    private final PessoaService pessoaService;
    private final GetPessoaPorFiltro getPessoaPorFiltro;

    public PessoaController(
        PessoaService pessoaService,
        GetPessoaPorFiltro getPessoaPorFiltro
    ) {
        this.pessoaService = pessoaService;
        this.getPessoaPorFiltro = getPessoaPorFiltro;
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

    @GetMapping("/pessoaFiltroAuxiliar")
    public ResponseEntity<?> getPessoaComFiltroAuxiliar(
        @RequestBody GetPessoaFiltroDto getPessoaFiltroDto
    ) {
        List<Pessoa> pessoasCoerentes = getPessoaPorFiltro.executar(getPessoaFiltroDto);
        return ResponseEntity.ok().body(pessoasCoerentes);
    }

    @GetMapping("/pessoaFiltro")
    public ResponseEntity<?> getPessoaComFiltro(
        @RequestParam(name = "idResponsavel", required = false) Long idResponsavel,
        @RequestParam(
            name = "nomeDoResponsavel",
            required = false
        ) String nomeDoResponsavel,
        @RequestParam(
            name = "situacaoPessoa",
            required = false
        ) SituacaoPessoa situacaoPessoa,
        @RequestParam(name = "tipoPessoa", required = false) TipoPessoa tipoPessoa
    ) {
        GetPessoaFiltroDto getPessoaFiltroDto = GetPessoaFiltroDto
            .builder()
            .idResponsavel(idResponsavel)
            .nomeDoResponsavel(nomeDoResponsavel)
            .situacaoPessoa(situacaoPessoa)
            .tipoPessoa(tipoPessoa)
            .build();

        List<Pessoa> pessoasCoerentes = getPessoaPorFiltro.executar(getPessoaFiltroDto);
        return ResponseEntity.ok().body(pessoasCoerentes);
    }

    @GetMapping("/getPorNome/{nome}")
    public ResponseEntity<?> getPessoaPorNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok().body(pessoaService.getPorNome(nome));
    }
}
