package com.ufms.olx.services;

import com.ufms.olx.domain.dto.PedidoDTO.PedidoDTO;
import com.ufms.olx.domain.entities.Pedido;
import com.ufms.olx.domain.entities.Pessoa;
import com.ufms.olx.domain.entities.Usuario;
import com.ufms.olx.domain.enums.TipoPessoa;
import com.ufms.olx.repository.PedidoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PedidoService implements GenericCRUDService<Pedido, PedidoDTO> {
    private final PedidoRepository repository;

    private final PessoaService pessoaService;

    private final UsuarioService usuarioService;

    public PedidoService(
        PedidoRepository repository,
        PessoaService pessoaService,
        UsuarioService usuarioService
    ) {
        this.repository = repository;
        this.pessoaService = pessoaService;
        this.usuarioService = usuarioService;
    }

    @Override
    public Pedido insert(PedidoDTO dto) {
        Pessoa p = pessoaService.getById(dto.getIdPessoa());
        BigDecimal percentualDesconto = p.getTipoPessoa().equals(TipoPessoa.FISICA)
            ? BigDecimal.valueOf(0.1)
            : BigDecimal.valueOf(0.2);

        Pedido pedido = Pedido
            .builder()
            .statusPedido(dto.getStatusPedido())
            .dataCompra(dto.getDataCompra())
            .dataEntrega(dto.getDataEntrega())
            .idPessoa(dto.getIdPessoa())
            .build();
        pedido.setPercentualDesconto(percentualDesconto);
        pedido = repository.save(pedido);
        return pedido;
    }

    @Override
    public List<Pedido> getAll() {
        return repository.findAll();
    }

    @Override
    public Pedido getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Error("Not Found"));
    }

    @Override
    public Pedido update(Pedido pedido, Long id) {
        var backup = this.getById(id);
        pedido.setId(backup.getId());
        return repository.save(pedido);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Pedido> getPedidoPorUsuario(String login, String senha) {
        Usuario usuario = usuarioService.login(login, senha);
        return repository.getPedidosByIdPessoa(usuario.getPessoa().getId());
    }

    public List<Pedido> getPedidoPorPessoa(Long id) {
        return repository.getPedidosByIdPessoa(id);
    }
}
