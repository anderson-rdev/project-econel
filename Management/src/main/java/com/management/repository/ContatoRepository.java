package com.management.repository;

import com.management.model.Contato;
import com.management.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    // Buscar todos os contatos de uma pessoa específica
    List<Contato> findByPessoa(Pessoa pessoa);

    // Buscar contatos pelo tipo (ex: EMAIL, CELULAR, TELEFONE)
    List<Contato> findByTipoContato_DescricaoIgnoreCase(String descricao);
}
