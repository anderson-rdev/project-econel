package com.management.DTOs;

/**
 * DTO para representar os dados de contato, tanto em requisições (entrada)
 * quanto em respostas (saída).
 *
 * No caso de requisições, o campo 'tipo' representa o nome/descrição do tipo de contato
 * (por exemplo: "EMAIL", "CELULAR", "TELEFONE").
 *
 * No caso de respostas, ele pode ser usado para retornar essa descrição.
 */
public class ContatoDTO {

    // Exemplo: "EMAIL", "CELULAR", "TELEFONE"
    private String tipo;

    // Exemplo: "usuario@email.com" ou "(11) 99999-8888"
    private String valor;

    public ContatoDTO() {}

    public ContatoDTO(String tipo, String valor) {
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
