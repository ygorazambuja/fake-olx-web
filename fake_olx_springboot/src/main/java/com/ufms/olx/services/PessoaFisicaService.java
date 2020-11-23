package com.ufms.olx.services;

import com.ufms.olx.domain.dto.PessoaDTO.PessoaFisicaDTO;
import com.ufms.olx.domain.entities.PessoaFisica;
import com.ufms.olx.repository.PessoaFisicaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService
    implements GenericCRUDService<PessoaFisica, PessoaFisicaDTO> {
    private final PessoaFisicaRepository repository;

    public PessoaFisicaService(PessoaFisicaRepository repository) {
        this.repository = repository;
    }

    @Override
    public PessoaFisica insert(PessoaFisicaDTO dto) {
        PessoaFisica pessoaFisica = PessoaFisica
            .fisicaBuilder()
            .apelido(dto.getApelido())
            .cpf(dto.getCpf())
            .dataNascimento(dto.getDataNascimento())
            .id(null)
            .idResponsavel(dto.getIdResponsavel())
            .nome(dto.getNome())
            .rg(dto.getRg())
            .situacaoPessoa(dto.getSituacaoPessoa())
            .tipoPessoa(dto.getTipoPessoa())
            .build();
        return repository.save(pessoaFisica);
    }

    @Override
    public PessoaFisica getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<PessoaFisica> getAll() {
        return repository.findAll();
    }

    @Override
    public PessoaFisica update(PessoaFisica pessoaFisica, Long id) {
        var backup = this.getById(id);
        pessoaFisica.setId(backup.getId());
        repository.save(pessoaFisica);

        return null;
    }

    @Override
    public void delete(Long id) {}
}
