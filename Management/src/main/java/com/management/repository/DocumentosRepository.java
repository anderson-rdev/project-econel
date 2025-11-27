package com.management.repository;

import com.management.model.Documentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentosRepository extends JpaRepository<Documentos, Long> {
    boolean existsByNumeroDocumentoAndTipoDocumento(String numeroDocumento, String tipoDocumento);
}
