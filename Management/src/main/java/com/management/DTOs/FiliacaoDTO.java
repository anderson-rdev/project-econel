package com.management.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa a filiação de uma pessoa, incluindo nome do pai e da mãe")
public class FiliacaoDTO {

    @Schema(description = "Nome do pai da pessoa", example = "José da Silva")
    private String nomePai;

    @Schema(description = "Nome da mãe da pessoa", example = "Maria da Silva")
    private String nomeMae;

    // Construtor vazio
    public FiliacaoDTO() {}

    // Construtor completo
    public FiliacaoDTO(String nomePai, String nomeMae) {
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
    }

    // Getters e Setters
    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    // ToString para facilitar logs e depuração
    @Override
    public String toString() {
        return "FiliacaoDTO{" +
                "nomePai='" + nomePai + '\'' +
                ", nomeMae='" + nomeMae + '\'' +
                '}';
    }
}
