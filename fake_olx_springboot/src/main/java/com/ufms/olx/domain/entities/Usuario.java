package com.ufms.olx.domain.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    private boolean adminstrador;

    @Column(unique = true)
    private String login;

    @Column
    private String senha;

    @OneToOne
    private Pessoa pessoa;
}
