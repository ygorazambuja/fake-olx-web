package com.ufms.olx.domain.dto.PedidoDTO;

import com.ufms.olx.domain.enums.StatusPedido;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {
    @NotEmpty
    private Long idPessoa;

    @NotEmpty
    private StatusPedido statusPedido;

    @NotEmpty
    private LocalDate dataCompra;

    @NotEmpty
    private LocalDate dataEntrega;
}
