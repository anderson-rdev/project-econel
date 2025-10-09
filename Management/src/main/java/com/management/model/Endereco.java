package com.management.model;

public class Endereco {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String tipo;   // Residencial, Comercial, etc.
    private Pessoa pessoa; // referência à Pessoa

    public Endereco(String rua, String numero, String bairro, String cidade, String estado, String cep, String tipo) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.tipo = tipo;
    }

    public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }
    public Pessoa getPessoa() { return pessoa; }
    public Long getPessoaId() { return pessoa != null ? pessoa.getId() : null; }
    // public String getPessoaNome() { return pessoa != null ? pessoa.getNome() : null; }

    @Override
    public String toString() {
        return tipo + ": " + rua + ", " + numero + " - " + bairro + ", " + cidade + "/" + estado +
                " (CEP: " + cep + ")";
    }
}