package com.management.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Representa um endereço de uma pessoa")
public class EnderecoDTO {

    @Schema(description = "Rua, avenida ou logradouro", example = "Rua das Flores")
    @NotBlank(message = "O logradouro é obrigatório")
    private String logradouro;

    @Schema(description = "Número do endereço", example = "123")
    @NotBlank(message = "O número é obrigatório")
    private String numero;

    @Schema(description = "Cidade do endereço", example = "Mogi das Cruzes")
    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @Schema(description = "Estado (sigla UF)", example = "SP")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres (sigla)")
    private String estado;

    @Schema(description = "CEP do endereço", example = "08773-000")
    @NotBlank(message = "O CEP é obrigatório")
    private String cep;

    // Getters e Setters
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

