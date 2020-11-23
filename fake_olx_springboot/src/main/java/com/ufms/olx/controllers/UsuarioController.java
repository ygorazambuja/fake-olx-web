package com.ufms.olx.controllers;

import com.ufms.olx.domain.dto.UsuarioDTO.UsuarioDTO;
import com.ufms.olx.domain.entities.Usuario;
import com.ufms.olx.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> insereUsuario(@RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.insere(dto);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.getById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<?> buscaPorLogin(@PathVariable("login") String login) {
        Usuario usuario = usuarioService.getByLogin(login);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/usuario/insereAdmin")
    public ResponseEntity<?> insereAdmin(@RequestBody UsuarioDTO dto) {
        var usuario = usuarioService.insereAdmin(dto);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(usuarioService.getAll());
    }
}
