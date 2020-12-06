package com.ufms.olx.controllers;

import com.ufms.olx.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/login")
public class LoginController {
    public final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> login(
        @RequestHeader("login") String login,
        @RequestHeader("senha") String senha
    ) {
        return ResponseEntity.ok().body(usuarioService.login(login, senha));
    }
}
