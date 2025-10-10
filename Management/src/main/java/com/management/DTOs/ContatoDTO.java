package com.management.DTOs;

import com.management.enums.Contatos;
import com.management.model.Contato;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Schema(description = "Representa os dados de contato de uma pessoa")
public class ContatoDTO extends Contato {

    @Schema(description = "Tipo do contato (ex: CELULAR, EMAIL)", example = "CELULAR", required = true)
    @NotNull
    private Contatos tipo;

    @Schema(description = "Valor do contato (ex: número de telefone ou email)", example = "(11) 99999-9999", required = true)
    @NotNull
    @Size(min = 10, max = 15)
    private String valor;

    public ContatoDTO(Contatos tipo, String valor) {
        super(tipo, valor);
        this.tipo = tipo;
        this.valor = valor;
    }

    public Contatos getTipo() {
        return tipo;
    }

    public void setTipo(Contatos tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
