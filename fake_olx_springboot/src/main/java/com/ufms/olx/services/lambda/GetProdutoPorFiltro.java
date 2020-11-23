package com.ufms.olx.services.lambda;

import com.ufms.olx.domain.entities.Produto;
import com.ufms.olx.services.ProdutoService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

public class GetProdutoPorFiltro {
    @Autowired
    private ProdutoService produtoService;

    public List<Produto> call() {
        return produtoService
            .getAll()
            .stream()
            .filter(
                produto -> {
                    return false;
                }
            )
            .collect(Collectors.toList());
    }
}
