package com.management.model;

public class Dominio {
    private Long id;
    private String nome;

    // Construtor vazio necessário para frameworks
    public Dominio() {
    }

    // Construtor com parâmetros
    public Dominio(String nome, Long id) {
        this.nome = nome;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
