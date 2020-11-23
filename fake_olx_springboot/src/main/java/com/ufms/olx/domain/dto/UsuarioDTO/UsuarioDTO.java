package com.ufms.olx.domain.dto.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private boolean isAdminstador;
    private String login;
    private String senha;
    private Long pessoaId;
}
