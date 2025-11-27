package com.management.DTOs;

import com.management.enums.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Representa um endereço de uma pessoa")
public class EnderecoDTO {

    @Schema(description = "Rua, avenida ou logradouro", example = "Rua das Flores")
    @NotBlank(message = "A rua é obrigatória")
    private String rua;

    @Schema(description = "Número do endereço", example = "123")
    @NotBlank(message = "O número é obrigatório")
    private String numero;

    @Schema(description = "Bairro do endereço", example = "Centro")
    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;

    @Schema(description = "Cidade do endereço", example = "Mogi das Cruzes")
    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @Schema(description = "Estado (sigla UF)", example = "SP")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres (sigla)")
    private String estado;

    @Schema(description = "CEP do endereço", example = "08773-000")
    @NotBlank(message = "O CEP é obrigatório")
    private String cep;

    @Schema(description = "Complemento do endereço", example = "Casa 1")
    private String complemento;

    @Schema(description = "Tipo do endereço (RESIDENCIAL, COMERCIAL, etc)", example = "RESIDENCIAL")
    @NotNull(message = "O tipo do endereço é obrigatório")
    private TipoEndereco tipo;

    // Getters e Setters
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public TipoEndereco getTipo() {
        return tipo;
    }

    public void setTipo(TipoEndereco tipo) {
        this.tipo = tipo;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
