package com.ufms.olx.repository;

import com.ufms.olx.domain.entities.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> getAllByIdadePermitidaLessThanEqual(Integer idade);
}
