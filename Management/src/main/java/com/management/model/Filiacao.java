package com.management.model;

public class Filiacao extends Pessoa {
    private String nomePai;
    private String nomeMae;

    // Construtor Padrão
    public Filiacao() {}

    public Filiacao(Long id, String nome, String nomePai, String nomeMae) {
        super(id, nome); // aproveita o construtor da Pessoa
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
    }

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
}
