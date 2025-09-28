package com.management.model;

public class Filiacao extends Dominio {
    private String nomePai;
    private String nomeMae;

    public Filiacao(Long id, String nome, String nomePai, String nomeMae) {
        super(nome, id);
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
