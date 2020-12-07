package com.ufms.olx.domain.dto.ProdutoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProdutoFiltroDto {
  private BigDecimal precoMinimo;
  private BigDecimal precoMaximo;
  private String descricao;
}
