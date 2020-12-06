package com.ufms.olx.repository;

import com.ufms.olx.domain.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public Pessoa findByNome(String nome);
}
