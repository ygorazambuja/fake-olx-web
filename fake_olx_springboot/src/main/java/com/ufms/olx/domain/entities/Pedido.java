package com.ufms.olx.domain.entities;

import com.ufms.olx.domain.enums.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idPessoa;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @DateTimeFormat
    private LocalDate dataCompra;

    @DateTimeFormat
    private LocalDate dataEntrega;

    private BigDecimal percentualDesconto;
}
