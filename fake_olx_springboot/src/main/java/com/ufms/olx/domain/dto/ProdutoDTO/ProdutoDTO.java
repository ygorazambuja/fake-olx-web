package com.ufms.olx.domain.dto.ProdutoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {
    private String descricao;
    private Long quantidadeEstoque;
    private Integer idadePermitida;
    private BigDecimal precoCompra;
    private BigDecimal precoVendaFisica;
    private BigDecimal precoVendaJuridica;
}
