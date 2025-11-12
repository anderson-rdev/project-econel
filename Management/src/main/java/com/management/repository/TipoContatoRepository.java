package com.management.repository;

import com.management.model.TipoContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoContatoRepository extends JpaRepository<TipoContato, Long> {
    Optional<TipoContato> findByDescricaoIgnoreCase(String descricao);
}
