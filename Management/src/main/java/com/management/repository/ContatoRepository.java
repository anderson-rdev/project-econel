package com.management.repository;

import com.management.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByPessoaIdPessoa(Long idPessoa);
}

