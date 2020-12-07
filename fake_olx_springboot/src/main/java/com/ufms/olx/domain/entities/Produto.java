package com.ufms.olx.domain.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descricao;
    private Long quantidadeEstoque;
    private Integer idadePermitida;
    private BigDecimal precoCompra;

    @Transient
    private BigDecimal precoVendaFisica;

    @Transient
    private BigDecimal precoVendaJuridica;
}
