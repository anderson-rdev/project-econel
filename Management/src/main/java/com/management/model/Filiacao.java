package com.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Filiacoes")
public class Filiacao extends Dominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePai;
    private String nomeMae;

    @ManyToOne
    @JoinColumn(name = "pessoa_id") // chave estrangeira para Pessoa
    private Pessoa pessoa;

    public Filiacao() {
        super();
    }

    public Filiacao(String nomePai, String nomeMae) {
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
