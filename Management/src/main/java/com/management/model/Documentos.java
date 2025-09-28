package com.management.model;

public class Documentos extends Pessoa {
    private String numeroDocumento;
    private String tipoDocumento;

    // Construtor Padrão
    public Documentos() {}

    public Documentos(Long id, String nome, String numeroDocumento, String tipoDocumento) {
        super(id, nome); // aproveita o construtor da Pessoa
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}

