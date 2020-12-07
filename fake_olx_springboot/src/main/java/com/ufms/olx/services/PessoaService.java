package com.ufms.olx.services;

import com.ufms.olx.domain.dto.PessoaDTO.PessoaDTO;
import com.ufms.olx.domain.dto.PessoaDTO.PessoaFisicaDTO;
import com.ufms.olx.domain.dto.PessoaDTO.PessoaJuridicaDTO;
import com.ufms.olx.domain.entities.Pessoa;
import com.ufms.olx.repository.PessoaFisicaRepository;
import com.ufms.olx.repository.PessoaJuridicaRepository;
import com.ufms.olx.repository.PessoaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PessoaService implements GenericCRUDService<Pessoa, PessoaDTO> {
  final PessoaFisicaService pessoaFisicaService;

  final PessoaJuricaService pessoaJuricaService;

  final PessoaRepository pessoaRepository;
  final PessoaJuridicaRepository pessoaJuridicaRepository;
  final PessoaFisicaRepository pessoaFisicaRepository;

  public PessoaService(
          PessoaFisicaService pessoaFisicaService,
          PessoaJuricaService pessoaJuricaService,
          PessoaRepository pessoaRepository,
          PessoaJuridicaRepository pessoaJuridicaRepository, PessoaFisicaRepository pessoaFisicaRepository) {
    this.pessoaFisicaService = pessoaFisicaService;
    this.pessoaJuricaService = pessoaJuricaService;
    this.pessoaRepository = pessoaRepository;
    this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    this.pessoaFisicaRepository = pessoaFisicaRepository;
  }

  @Override
  public Pessoa getById(Long id) {
    return pessoaRepository.findById(id).orElseThrow();
  }

  @Override
  public void delete(Long id) {
    pessoaRepository.deleteById(id);
  }

  @Override
  public Pessoa insert(PessoaDTO entity) {
    return inserePessoaFisicaOuJuridica(entity);
  }

  @Override
  public Pessoa update(Pessoa pessoa, Long id) {
    return null;
  }

  public Pessoa updatePessoa(PessoaDTO pessoa, Long id) {
    if (pessoa.getCnpj() == null) {
      return pessoaFisicaRepository.findById(id).map(
              record -> {
                record.setNome(pessoa.getNome());
                record.setApelido(pessoa.getApelido());
                record.setDataNascimento(pessoa.getDataNascimento());
                record.setSituacaoPessoa(pessoa.getSituacaoPessoa());
                record.setTipoPessoa(pessoa.getTipoPessoa());
                return pessoaFisicaRepository.save(record);
              }
      ).orElseThrow(() -> new Error("Pessoa não existe"));
    } else {
      return pessoaJuridicaRepository.findById(id).map(
              record -> {
                record.setNome(pessoa.getNome());
                record.setApelido(pessoa.getApelido());
                record.setDataNascimento(pessoa.getDataNascimento());
                record.setSituacaoPessoa(pessoa.getSituacaoPessoa());
                record.setTipoPessoa(pessoa.getTipoPessoa());
                return pessoaJuridicaRepository.save(record);
              }
      ).orElseThrow(() -> new Error("Pessoa não existe"));
    }

  }

  @Override
  public List<Pessoa> getAll() {
    return pessoaRepository.findAll();
  }

  private Pessoa inserePessoaFisicaOuJuridica(PessoaDTO dto) {
    if (dto.getCpf() == null && dto.getRg() == null && !dto.getCnpj().isEmpty()) {
      var juridicaDto = PessoaJuridicaDTO
              .builder()
              .apelido(dto.getApelido())
              .cnpj(dto.getCnpj())
              .nome(dto.getNome())
              .dataNascimento(dto.getDataNascimento())
              .id(null)
              .idResponsavel(dto.getIdResponsavel())
              .situacaoPessoa(dto.getSituacaoPessoa())
              .tipoPessoa(dto.getTipoPessoa())
              .build();

      if (validaIdade(juridicaDto.getDataNascimento())) {
        return pessoaJuricaService.insert(juridicaDto);
      } else {
        if (
                juridicaDto.getIdResponsavel() != null &&
                        validaIdResponsavel(juridicaDto.getIdResponsavel())
        ) {
          return pessoaJuricaService.insert(juridicaDto);
        } else {
          return null;
        }
      }
    } else {
      PessoaFisicaDTO fisicaDto = PessoaFisicaDTO
              .builder()
              .apelido(dto.getApelido())
              .cpf(dto.getCpf())
              .dataNascimento(dto.getDataNascimento())
              .id(null)
              .rg(dto.getRg())
              .idResponsavel(dto.getIdResponsavel())
              .situacaoPessoa(dto.getSituacaoPessoa())
              .nome(dto.getNome())
              .tipoPessoa(dto.getTipoPessoa())
              .build();
      return pessoaFisicaService.insert(fisicaDto);
    }
  }

  private boolean validaIdade(LocalDate dataNascimento) {
    return Period.between(LocalDate.now(), dataNascimento).getYears() * -1 > 18;
  }

  private boolean validaIdResponsavel(Long id) {
    var responsavel = pessoaRepository.findById(id).orElse(null);
    return responsavel != null && validaIdade(responsavel.getDataNascimento());
  }

  public Pessoa getPorNome(String nome) {
    return pessoaRepository.findByNome(nome);
  }
}
