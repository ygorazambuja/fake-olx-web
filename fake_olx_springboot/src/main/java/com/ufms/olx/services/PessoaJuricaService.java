package com.ufms.olx.services;

import com.ufms.olx.domain.dto.PessoaDTO.PessoaJuridicaDTO;
import com.ufms.olx.domain.entities.PessoaJuridica;
import com.ufms.olx.repository.PessoaJuridicaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PessoaJuricaService
    implements GenericCRUDService<PessoaJuridica, PessoaJuridicaDTO> {
    private final PessoaJuridicaRepository repository;

    public PessoaJuricaService(PessoaJuridicaRepository repository) {
        this.repository = repository;
    }

    @Override
    public PessoaJuridica insert(PessoaJuridicaDTO dto) {
        PessoaJuridica pessoa = PessoaJuridica
            .pessoaJuridica()
            .apelido(dto.getApelido())
            .cnpj(dto.getCnpj())
            .dataNascimento(dto.getDataNascimento())
            .id(null)
            .idResponsavel(dto.getIdResponsavel())
            .nome(dto.getNome())
            .situacaoPessoa(dto.getSituacaoPessoa())
            .tipoPessoa(dto.getTipoPessoa())
            .build();
        return repository.save(pessoa);
    }

    @Override
    public PessoaJuridica update(PessoaJuridica pessoaJuridica, Long id) {
        return null;
    }

    @Override
    public PessoaJuridica getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<PessoaJuridica> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
