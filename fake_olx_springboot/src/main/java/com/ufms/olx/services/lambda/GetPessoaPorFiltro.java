package com.ufms.olx.services.lambda;

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
        return pessoaRepository
            .findAll()
            .stream()
            .filter(
                pessoa -> {
                    if (
                        pessoa.getIdResponsavel() == null ||
                        getPessoaFiltroDto.getIdResponsavel() == null
                    ) {
                        return false;
                    } else {
                        return pessoa
                            .getIdResponsavel()
                            .equals(getPessoaFiltroDto.getIdResponsavel());
                    }
                }
            )
//            .filter(
//                pessoa -> {
//                    if (
//                        pessoa.getSituacaoPessoa() == null ||
//                        getPessoaFiltroDto.getSituacaoPessoa() == null
//                    ) {
//                        return false;
//                    } else {
//                        return pessoa
//                            .getSituacaoPessoa()
//                            .equals(getPessoaFiltroDto.getSituacaoPessoa());
//                    }
//                }
//            )
//            .filter(
//                pessoa -> {
//                    if (
//                        pessoa.getTipoPessoa() == null ||
//                        getPessoaFiltroDto.getSituacaoPessoa() == null
//                    ) {
//                        return false;
//                    } else {
//                        return pessoa
//                            .getTipoPessoa()
//                            .equals(getPessoaFiltroDto.getTipoPessoa());
//                    }
//                }
//            )
            .collect(Collectors.toList());
    }
}
