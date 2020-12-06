package com.ufms.olx.services.lambda;

import static java.util.Objects.nonNull;

import com.ufms.olx.domain.dto.PessoaDTO.GetPessoaFiltroDto;
import com.ufms.olx.domain.entities.Pessoa;
import com.ufms.olx.repository.PessoaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GetPessoaPorFiltro {
    private final PessoaRepository pessoaRepository;

    public GetPessoaPorFiltro(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> executar(GetPessoaFiltroDto getPessoaFiltroDto) {
        Pessoa responsavel = pessoaRepository.findByNome(
            getPessoaFiltroDto.getNomeDoResponsavel()
        );
        return pessoaRepository
            .findAll()
            .stream()
            .filter(
                pessoa -> {
                    if (nonNull(getPessoaFiltroDto.getTipoPessoa())) {
                        return pessoa
                            .getTipoPessoa()
                            .equals(getPessoaFiltroDto.getTipoPessoa());
                    }
                    return Boolean.TRUE;
                }
            )
            .filter(
                pessoa -> {
                    if (nonNull(getPessoaFiltroDto.getIdResponsavel())) {
                        return pessoa
                            .getIdResponsavel()
                            .equals(getPessoaFiltroDto.getIdResponsavel());
                    }
                    return Boolean.TRUE;
                }
            )
            .filter(
                pessoa -> {
                    if (nonNull(getPessoaFiltroDto.getSituacaoPessoa())) {
                        return pessoa
                            .getSituacaoPessoa()
                            .equals(getPessoaFiltroDto.getSituacaoPessoa());
                    }
                    return Boolean.TRUE;
                }
            )
            .filter(
                pessoa -> {
                    if (nonNull(responsavel)) {
                        return nonNull(pessoa.getIdResponsavel())
                            ? pessoa.getIdResponsavel().equals(responsavel.getId())
                            : Boolean.FALSE;
                    }
                    return Boolean.TRUE;
                }
            )
            .collect(Collectors.toList());
    }
}
