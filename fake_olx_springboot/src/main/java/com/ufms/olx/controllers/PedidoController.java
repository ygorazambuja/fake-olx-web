package com.ufms.olx.controllers;

import com.ufms.olx.domain.dto.PedidoDTO.PedidoDTO;
import com.ufms.olx.domain.entities.Pedido;
import com.ufms.olx.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/pedido")
public class PedidoController implements GenericController<Pedido, PedidoDTO> {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> addEntity(@RequestBody PedidoDTO dto) {
        Pedido pedido = pedidoService.insert(dto);
        return ResponseEntity.ok().body(pedido);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getAllEntities() {
        return ResponseEntity.ok().body(pedidoService.getAll());
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEntityById(@PathVariable Long id) {
        return ResponseEntity.ok().body(pedidoService.getById(id));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntity(
        @RequestBody Pedido pedido,
        @PathVariable Long id
    ) {
        Pedido pedidoAtualizado = pedidoService.update(pedido, id);
        return ResponseEntity.ok().body(pedidoAtualizado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEntity(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/usuario")
    public ResponseEntity<?> getPedidoPorUsuario(
        @RequestHeader("login") String login,
        @RequestHeader("senha") String senha
    ) {
        return ResponseEntity.ok().body(pedidoService.getPedidoPorUsuario(login, senha));
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<?> getPedidoPorPessoa(@PathVariable Long id) {
        return ResponseEntity.ok().body(pedidoService.getPedidoPorPessoa(id));
    }
}
