package com.ufms.olx.repository;

import com.ufms.olx.domain.entities.ItemPedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    public List<ItemPedido> getAllByPedido_IdPessoa(Long id);
}
