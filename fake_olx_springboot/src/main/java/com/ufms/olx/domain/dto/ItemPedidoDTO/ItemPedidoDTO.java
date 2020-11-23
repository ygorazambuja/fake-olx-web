package com.ufms.olx.domain.dto.ItemPedidoDTO;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    @NotNull
    private Long pedidoId;

    @NotNull
    private Long produtoId;

    @NotNull
    private Long quantidade;
}
