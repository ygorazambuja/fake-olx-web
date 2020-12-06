package com.ufms.olx.configs;

import com.ufms.olx.domain.dto.PessoaDTO.PessoaDTO;
import com.ufms.olx.domain.entities.*;
import com.ufms.olx.domain.enums.SituacaoPessoa;
import com.ufms.olx.domain.enums.StatusPedido;
import com.ufms.olx.domain.enums.TipoPessoa;
import com.ufms.olx.repository.*;
import com.ufms.olx.services.PessoaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("developer")
public class InitialConfig {
    private final PessoaRepository pessoaRepository;

    private final UsuarioRepository usuarioRepository;

    private final ProdutoRepository produtoRepository;

    private final PedidoRepository pedidoRepository;

    private final PessoaService pessoaService;

    private final ItemPedidoRepository itemPedidoRepository;

    public InitialConfig(
        PessoaRepository pessoaRepository,
        UsuarioRepository usuarioRepository,
        ProdutoRepository produtoRepository,
        PedidoRepository pedidoRepository,
        PessoaService pessoaService,
        ItemPedidoRepository itemPedidoRepository
    ) {
        this.pessoaRepository = pessoaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.pessoaService = pessoaService;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Bean("beanConfiguration")
    public CommandLineRunner run() {
        return args -> {
            System.out.println("Developer Mode");
            PopulaPessoas();
            PopulaUsuarios();
            PopulaProdutos();
            PopulaPedidos();
            PopulaItemPedido();
        };
    }

    private void PopulaPessoas() {
        Pessoa pessoa1 = PessoaFisica
            .fisicaBuilder()
            .id(null)
            .situacaoPessoa(SituacaoPessoa.ATIVO)
            .tipoPessoa(TipoPessoa.FISICA)
            .cpf("33380238040")
            .rg("141695067")
            .apelido("Pessoa1")
            .dataNascimento(LocalDate.parse("2000-10-10"))
            .nome("Pessoa 1")
            .idResponsavel(null)
            .build();

        Pessoa pessoa2 = PessoaFisica
            .fisicaBuilder()
            .id(null)
            .situacaoPessoa(SituacaoPessoa.ATIVO)
            .tipoPessoa(TipoPessoa.FISICA)
            .cpf("62922060098")
            .rg("466508244")
            .apelido("Pessoa2")
            .dataNascimento(LocalDate.parse("2010-10-10"))
            .nome("Pessoa 2")
            .idResponsavel(1L)
            .build();

        Pessoa pessoa3 = PessoaFisica
            .fisicaBuilder()
            .id(null)
            .situacaoPessoa(SituacaoPessoa.ATIVO)
            .tipoPessoa(TipoPessoa.FISICA)
            .cpf("17687296036")
            .rg("478501389")
            .apelido("Pessoa3")
            .dataNascimento(LocalDate.parse("2010-10-10"))
            .nome("Pessoa 3")
            .idResponsavel(null)
            .build();

        PessoaDTO pessoaJuridica = PessoaDTO
            .builder()
            .situacaoPessoa(SituacaoPessoa.ATIVO)
            .tipoPessoa(TipoPessoa.JURIDICA)
            .cnpj("78637531000187")
            .nome("Pessoa Juridica 1")
            .idResponsavel(1L)
            .apelido("PessoaJuridica1")
            .dataNascimento(LocalDate.parse("2010-10-20"))
            .build();

        pessoaRepository.saveAll(Arrays.asList(pessoa1, pessoa2, pessoa3));
        pessoaService.insert(pessoaJuridica);
    }

    private void PopulaUsuarios() {
        Pessoa pessoa = pessoaRepository.findById(1L).orElse(null);
        Usuario usuario = Usuario
            .builder()
            .adminstrador(true)
            .senha("admin")
            .login("admin")
            .pessoa(pessoa)
            .build();
        Pessoa pessoaNova = pessoaRepository.findById(2L).orElse(null);

        Usuario usuarioNovinho = Usuario
            .builder()
            .adminstrador(false)
            .login("novinho")
            .senha("novinho")
            .pessoa(pessoaNova)
            .build();

        usuarioRepository.saveAll(Arrays.asList(usuario, usuarioNovinho));
    }

    private void PopulaProdutos() {
        Produto produto1 = Produto
            .builder()
            .descricao("Computador")
            .idadePermitida(0)
            .precoCompra(2000)
            .precoVendaFisica(2000)
            .precoVendaJuridica(1900)
            .quantidadeEstoque(1000L)
            .build();

        Produto produto2 = Produto
            .builder()
            .descricao("Casa")
            .idadePermitida(18)
            .precoCompra(1000000)
            .precoVendaFisica(1000000)
            .precoVendaJuridica(999999)
            .quantidadeEstoque(1L)
            .build();

        Produto produto3 = Produto
            .builder()
            .descricao("Moto")
            .idadePermitida(16)
            .precoCompra(10000)
            .precoVendaFisica(10000)
            .precoVendaJuridica(8000)
            .quantidadeEstoque(1L)
            .build();

        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
    }

    private void PopulaPedidos() {
        Pedido pedido1 = Pedido
            .builder()
            .dataCompra(LocalDate.now())
            .dataEntrega(LocalDate.now().plusDays(2))
            .percentualDesconto(BigDecimal.valueOf(5.0))
            .statusPedido(StatusPedido.REALIZADO)
            .idPessoa(1L)
            .build();

        Pedido pedido2 = Pedido
            .builder()
            .dataCompra(LocalDate.now())
            .dataEntrega(LocalDate.now().plusDays(6))
            .percentualDesconto(BigDecimal.valueOf(15.0))
            .statusPedido(StatusPedido.REALIZADO)
            .idPessoa(2L)
            .build();

        Pedido pedido3 = Pedido
            .builder()
            .dataCompra(LocalDate.now())
            .dataEntrega(LocalDate.now().plusDays(7))
            .percentualDesconto(BigDecimal.valueOf(20.0))
            .statusPedido(StatusPedido.REALIZADO)
            .idPessoa(3L)
            .build();
        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));
    }

    private void PopulaItemPedido() {
        Pedido pedido1 = pedidoRepository.findById(5L).orElse(null);
        Produto produto1 = produtoRepository.findById(4L).orElse(null);
        ItemPedido itemPedido = ItemPedido
            .builder()
            .pedido(pedido1)
            .produto(produto1)
            .quantidade(1L)
            .build();

        itemPedidoRepository.save(itemPedido);
    }
}
