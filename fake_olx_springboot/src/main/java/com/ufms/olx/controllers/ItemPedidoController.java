package com.ufms.olx.controllers;

import com.ufms.olx.domain.dto.ItemPedidoDTO.ItemPedidoDTO;
import com.ufms.olx.domain.entities.ItemPedido;
import com.ufms.olx.services.ItemPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/itemPedido")
public class ItemPedidoController
    implements GenericController<ItemPedido, ItemPedidoDTO> {
    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> addEntity(ItemPedidoDTO dto) {
        return ResponseEntity.ok().body(itemPedidoService.insert(dto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEntity(Long id) {
        itemPedidoService.delete(id);
        return ResponseEntity.ok().body("");
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntity(ItemPedido entity, Long id) {
        return ResponseEntity.ok().body(itemPedidoService.update(entity, id));
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getAllEntities() {
        return ResponseEntity.ok(itemPedidoService.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getEntityById(Long id) {
        return ResponseEntity.ok().body(itemPedidoService.getById(id));
    }

    @GetMapping("/usuario")
    public ResponseEntity<?> getItemPedidoPorUsuario(
        @RequestHeader("login") String login,
        @RequestHeader("senha") String senha
    ) {
        return ResponseEntity
            .ok()
            .body(itemPedidoService.getItemPedidoPorUsuario(login, senha));
    }
}
