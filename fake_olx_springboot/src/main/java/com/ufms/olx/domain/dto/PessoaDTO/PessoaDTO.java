package com.ufms.olx.domain.dto.PessoaDTO;

import com.ufms.olx.domain.enums.SituacaoPessoa;
import com.ufms.olx.domain.enums.TipoPessoa;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaDTO {
    private Long id, idResponsavel;
    private TipoPessoa tipoPessoa;
    private SituacaoPessoa situacaoPessoa;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String apelido;

    private String cpf, rg, cnpj;
    private LocalDate dataNascimento;
}
