package com.management.DTOs;

import java.util.List;

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
    private String tipo;   // Exemplo: "Email", "Telefone"
    private String valor;  // Exemplo: "usuario@dominio.com"
    private List<ContatoDTO> contatos;

    public ContatoDTO() {}

    public ContatoDTO(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

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

    public List<ContatoDTO> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoDTO> contatos) {
        this.contatos = contatos;
    }
}
