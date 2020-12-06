package com.ufms.olx.domain.dto.ProdutoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {
    private String descricao;
    private Long quantidadeEstoque;
    private Integer idadePermitida;
    private double precoCompra;
    private double precoVendaFisica;
    private double precoVendaJuridica;
}
