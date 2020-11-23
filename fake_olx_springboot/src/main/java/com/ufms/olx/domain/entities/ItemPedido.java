package com.ufms.olx.domain.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido.id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto.id")
    private Produto produto;

    private Long quantidade;
}
