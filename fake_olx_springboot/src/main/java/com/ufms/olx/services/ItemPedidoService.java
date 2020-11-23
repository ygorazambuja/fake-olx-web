package com.ufms.olx.services;

import com.ufms.olx.domain.dto.ItemPedidoDTO.ItemPedidoDTO;
import com.ufms.olx.domain.entities.ItemPedido;
import com.ufms.olx.domain.entities.Pedido;
import com.ufms.olx.domain.entities.Produto;
import com.ufms.olx.domain.entities.Usuario;
import com.ufms.olx.repository.ItemPedidoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService implements GenericCRUDService<ItemPedido, ItemPedidoDTO> {
    private final ItemPedidoRepository itemPedidoRepository;

    private final PedidoService pedidoService;

    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;

    public ItemPedidoService(
        ItemPedidoRepository repository,
        PedidoService pedidoService,
        ProdutoService produtoService,
        UsuarioService usuarioService
    ) {
        this.itemPedidoRepository = repository;
        this.pedidoService = pedidoService;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
    }

    @Override
    public ItemPedido insert(ItemPedidoDTO itemPedidoDto) {
        Pedido pedido = pedidoService.getById(itemPedidoDto.getPedidoId());
        Produto produto = produtoService.getById(itemPedidoDto.getProdutoId());

        ItemPedido itemPedido = ItemPedido
            .builder()
            .pedido(pedido)
            .produto(produto)
            .quantidade(itemPedidoDto.getQuantidade())
            .build();

        return itemPedidoRepository.save(itemPedido);
    }

    @Override
    public ItemPedido update(ItemPedido itemPedido, Long id) {
        var backup = this.getById(id);
        itemPedido.setId(backup.getId());
        return itemPedidoRepository.save(itemPedido);
    }

    @Override
    public ItemPedido getById(Long id) {
        return itemPedidoRepository.findById(id).orElseThrow(() -> new Error(""));
    }

    @Override
    public List<ItemPedido> getAll() {
        return itemPedidoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        itemPedidoRepository.deleteById(id);
    }

    public List<ItemPedido> getItemPedidoPorUsuario(String login, String senha) {
        Usuario usuario = usuarioService.login(login, senha);
        return itemPedidoRepository.getAllByPedido_IdPessoa(usuario.getPessoa().getId());
    }
}
