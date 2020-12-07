package com.ufms.olx.services.lambda;

import com.ufms.olx.domain.entities.Produto;
import com.ufms.olx.domain.entities.Usuario;
import com.ufms.olx.repository.ProdutoRepository;
import com.ufms.olx.repository.UsuarioRepository;
import com.ufms.olx.services.ProdutoService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import com.ufms.olx.services.UsuarioService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class GetProdutoPorFiltro {
  private ProdutoRepository repository;
  private UsuarioService usuarioService;

  GetProdutoPorFiltro(ProdutoRepository repository, UsuarioService usuarioService) {
    this.repository = repository;
    this.usuarioService = usuarioService;
  }

  public List<Produto> filtrar(String descricao, BigDecimal precoMaximo, BigDecimal precoMinimo, String login, String senha) {
    Usuario usuario = usuarioService.login(login, senha);

    return repository.findAll().stream()
            .filter(produto -> {
              if (nonNull(produto.getId()) && nonNull(descricao)) {
                return isNull(produto.getDescricao()) ? Boolean.FALSE : produto.getDescricao().equals(descricao);
              } else if (nonNull(produto.getId())) {
                return Boolean.TRUE;
              }
              return Boolean.FALSE;
            })
            .filter(produto -> {
              if (nonNull(produto.getId()) && nonNull(precoMinimo)) {
                return produto.getPrecoCompra().compareTo(precoMinimo) >= 0;
              } else if (nonNull(produto.getId())) {
                return Boolean.TRUE;
              }
              return Boolean.FALSE;
            })
            .filter(produto -> {
              if (nonNull(produto.getId()) && nonNull(precoMaximo)) {
                return produto.getPrecoCompra().compareTo(precoMaximo) <= 0;
              } else if (nonNull(produto.getId())) {
                return Boolean.TRUE;
              }
              return Boolean.FALSE;
            })
            .filter(produto -> {
              if (nonNull(produto.getId()) && nonNull(usuario)) {
                if (nonNull(produto.getIdadePermitida())) {
                  Period idade = Period.between(usuario.getPessoa().getDataNascimento(), LocalDate.now());
                  System.out.println(idade);
                  return idade.getYears() > produto.getIdadePermitida();
                }

                return Boolean.FALSE;
              }

              return Boolean.FALSE;
            })
            .collect(Collectors.toList());
  }
}
