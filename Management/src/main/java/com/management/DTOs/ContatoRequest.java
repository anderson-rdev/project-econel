package com.management.DTOs;

/**
 * DTO usado nas requisições (POST/PUT) para cadastrar ou atualizar contatos.
 *
 * O campo 'tipo' deve conter o nome/descrição do tipo de contato
 * (por exemplo: "EMAIL", "CELULAR", "TELEFONE").
 */
public class ContatoRequest {

    private String tipo;  // descrição do tipo de contato
    private String valor; // e-mail, telefone, etc.

    public ContatoRequest() {}

    public ContatoRequest(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    // Getters e Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return tipo + ": " + valor;
    }
}
