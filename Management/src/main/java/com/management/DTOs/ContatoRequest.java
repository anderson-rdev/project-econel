package com.management.DTOs;

import com.management.enums.TipoContato;

// DTO para os dados de contato que vêm na request
public class ContatoRequest {
    private TipoContato tipo;
    private String valor;

    // Getters e Setters
    public TipoContato getTipo() { return tipo; }
    public void setTipo(TipoContato tipo) { this.tipo = tipo; }

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
}