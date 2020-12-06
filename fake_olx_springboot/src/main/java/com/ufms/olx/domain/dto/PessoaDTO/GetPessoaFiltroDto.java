package com.ufms.olx.domain.dto.PessoaDTO;

import com.ufms.olx.domain.enums.SituacaoPessoa;
import com.ufms.olx.domain.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPessoaFiltroDto {
    private Long idResponsavel;
    private String nomeDoResponsavel;
    private TipoPessoa tipoPessoa;
    private SituacaoPessoa situacaoPessoa;
}
