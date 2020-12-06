package com.ufms.olx.repository;

import com.ufms.olx.domain.entities.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    public List<Pedido> getPedidosByIdPessoa(Long id);
}
