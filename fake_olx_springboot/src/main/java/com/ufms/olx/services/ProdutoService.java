package com.ufms.olx.services;

import com.ufms.olx.domain.dto.ProdutoDTO.ProdutoDTO;
import com.ufms.olx.domain.entities.Produto;
import com.ufms.olx.domain.entities.Usuario;
import com.ufms.olx.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProdutoService implements GenericCRUDService<Produto, ProdutoDTO> {
  final ProdutoRepository repository;

  final UsuarioService usuarioService;

  public ProdutoService(ProdutoRepository repository, UsuarioService usuarioService) {
    this.repository = repository;
    this.usuarioService = usuarioService;
  }

  @Override
  public Produto insert(ProdutoDTO dto) {
    System.out.println(dto.getIdadePermitida());
    Produto produto = Produto
            .builder()
            .descricao(dto.getDescricao())
            .idadePermitida(dto.getIdadePermitida() == null ? 0 : dto.getIdadePermitida())
            .quantidadeEstoque(dto.getQuantidadeEstoque())
            .precoCompra(dto.getPrecoCompra())
            .precoVendaFisica(dto.getPrecoVendaFisica())
            .precoVendaJuridica(dto.getPrecoVendaJuridica())
            .build();
    return repository.save(produto);
  }

  @Override
  public Produto update(Produto produto, Long id) {
    return repository.findById(id)
            .map(record -> {
              record.setDescricao(produto.getDescricao());
              record.setIdadePermitida(produto.getIdadePermitida());
              record.setPrecoCompra(produto.getPrecoCompra());
              record.setPrecoVendaFisica(produto.getPrecoVendaFisica());
              record.setQuantidadeEstoque(produto.getQuantidadeEstoque());
              record.setPrecoVendaJuridica(produto.getPrecoVendaJuridica());
              return repository.save(record);
            }).orElseThrow(() -> new Error("Produto não existe"));
  }

  public List<Produto> getAll() {
    return repository.findAll();
  }

  public Produto getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new Error("Produto não existe"));
  }

  public List<Produto> getPorIdade(String login, String senha) {
    Usuario usuario = usuarioService.login(login, senha);
    if (usuario != null) {
      repository.getAllByIdadePermitidaLessThanEqual(
              usuario.getPessoa().getIdade()
      );
      return repository.getAllByIdadePermitidaLessThanEqual(
              usuario.getPessoa().getIdade()
      );
    } else {
      return null;
    }
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }


}
