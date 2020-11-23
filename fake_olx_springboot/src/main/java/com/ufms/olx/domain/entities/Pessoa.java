package com.ufms.olx.domain.entities;

import com.ufms.olx.domain.enums.SituacaoPessoa;
import com.ufms.olx.domain.enums.TipoPessoa;
import java.time.LocalDate;
import java.time.Period;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column
    private Long id;

    private Long idResponsavel;

    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    @Enumerated(EnumType.STRING)
    private SituacaoPessoa situacaoPessoa;

    private String nome;
    private String apelido;
    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public boolean validaIdade() {
        return Period.between(LocalDate.now(), this.dataNascimento).getYears() * -1 > 18;
    }

    public Integer getIdade() {
        return Period.between(LocalDate.now(), this.dataNascimento).getYears() * -1;
    }
}
