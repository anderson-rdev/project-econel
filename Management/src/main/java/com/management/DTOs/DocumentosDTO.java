package com.management.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa os documentos de uma pessoa, como RG, CPF, passaporte etc.")
public class DocumentosDTO {

    @Schema(description = "Número do documento", example = "123456789")
    private String numeroDocumento;

    @Schema(description = "Tipo do documento", example = "CPF")
    private String tipoDocumento;

    // Construtor vazio
    public DocumentosDTO() {}

    // Construtor completo
    public DocumentosDTO(String numeroDocumento, String tipoDocumento) {
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
    }

    // Getters e Setters
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

    // ToString para logs e depuração
    @Override
    public String toString() {
        return "DocumentosDTO{" +
                "numeroDocumento='" + numeroDocumento + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                '}';
    }
}
