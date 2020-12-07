package com.ufms.olx.controllers;

import com.ufms.olx.domain.dto.ProdutoDTO.GetProdutoFiltroDto;
import com.ufms.olx.domain.dto.ProdutoDTO.ProdutoDTO;
import com.ufms.olx.domain.entities.Produto;
import com.ufms.olx.services.ProdutoService;

import java.math.BigDecimal;
import java.util.List;

import com.ufms.olx.services.lambda.GetPessoaPorFiltro;
import com.ufms.olx.services.lambda.GetProdutoPorFiltro;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/produto")
public class ProdutoController {
  final ProdutoService produtoService;
  final GetProdutoPorFiltro getProdutoPorFiltro;

  public ProdutoController(ProdutoService produtoService, GetProdutoPorFiltro getProdutoPorFiltro) {
    this.produtoService = produtoService;
    this.getProdutoPorFiltro = getProdutoPorFiltro;
  }

  @PostMapping
  public ResponseEntity<?> insere(@RequestBody ProdutoDTO dto) {
    Produto produto = produtoService.insert(dto);
    return ResponseEntity.ok().body(produto);
  }

  @GetMapping
  public ResponseEntity<?> getProdutos(
          @RequestHeader("login") String login,
          @RequestHeader("senha") String senha
  ) {
    List<Produto> produtos = produtoService.getPorIdade(login, senha);
    return ResponseEntity.ok().body(produtos);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getProdutoById(@PathVariable Long id) {
    return ResponseEntity.ok().body(produtoService.getById(id));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<?> updateProduto(
          @PathVariable Long id,
          @RequestBody Produto produto
  ) {
    Produto produtoAtualizado = produtoService.update(produto, id);
    return ResponseEntity.ok().body(produtoAtualizado);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
    produtoService.delete(id);
    return ResponseEntity.ok().body("");
  }

  @GetMapping(path = "/produtoFiltro")
  public ResponseEntity<?> getProdutoFiltro(
          @RequestParam(value = "precoMinimo", required = false) BigDecimal precoMinimo,
          @RequestParam(value = "precoMaximo", required = false) BigDecimal precoMaximo,
          @RequestParam(value = "descricao", required = false) String descricao,
          @RequestHeader("login") String login,
          @RequestHeader("senha") String senha
  ) {
    List<Produto> produtos = getProdutoPorFiltro.filtrar(descricao, precoMaximo, precoMinimo, login, senha);
    return ResponseEntity.ok().body(produtos);
  }
}
