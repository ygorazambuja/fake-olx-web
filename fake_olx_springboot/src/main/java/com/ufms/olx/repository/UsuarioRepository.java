package com.ufms.olx.repository;

import com.ufms.olx.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findUsuarioByLogin(String login);
}
