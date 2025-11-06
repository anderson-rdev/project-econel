package com.management.repository;

import com.management.model.TipoContato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoContatoRepository extends JpaRepository<TipoContato, Long> {
    Optional<TipoContato> findByDescricaoIgnoreCase(String s);
}