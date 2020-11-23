package com.ufms.olx.services;

import com.ufms.olx.domain.dto.UsuarioDTO.UsuarioDTO;
import com.ufms.olx.domain.entities.Pessoa;
import com.ufms.olx.domain.entities.Usuario;
import com.ufms.olx.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements GenericCRUDService<Usuario, UsuarioDTO> {
    private final PessoaService pessoaService;

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(
        PessoaService pessoaService,
        UsuarioRepository usuarioRepository
    ) {
        this.pessoaService = pessoaService;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario insere(UsuarioDTO dto) {
        Pessoa pessoa = pessoaService.getById(dto.getPessoaId());
        Usuario usuario = Usuario
            .builder()
            .adminstrador(dto.isAdminstador())
            .login(dto.getLogin())
            .senha(dto.getSenha())
            .pessoa(pessoa)
            .build();
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario insert(UsuarioDTO entity) {
        Pessoa pessoa = pessoaService.getById(entity.getPessoaId());
        Usuario usuario = Usuario
            .builder()
            .adminstrador(entity.isAdminstador())
            .login(entity.getLogin())
            .senha(entity.getSenha())
            .pessoa(pessoa)
            .build();

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario, Long id) {
        var backup = this.getById(id);
        usuario.setId(backup.getId());
        return usuarioRepository.save(usuario);
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario getByLogin(String login) {
        return usuarioRepository.findUsuarioByLogin(login);
    }

    public Usuario insereAdmin(UsuarioDTO dto) {
        Pessoa pessoa = pessoaService.getById(dto.getPessoaId());
        Usuario usuario = Usuario
            .builder()
            .login("admin")
            .senha("admin")
            .pessoa(pessoa)
            .adminstrador(true)
            .build();
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String login, String senha) {
        Usuario usuario = this.getByLogin(login);
        if (usuario == null) return null;
        if (usuario.getSenha().equals(senha)) {
            return usuario;
        } else {
            return null;
        }
    }
}
