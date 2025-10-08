package com.management.model;

import com.management.enums.TipoSanguineo;
import com.management.enums.Contatos;

import java.util.ArrayList;
import java.util.List;

public class Pessoa extends Dominio{
    private Long id;
    private String nome;
    private List<Endereco> enderecos = new ArrayList<>();
    private TipoSanguineo tipoSanguineo;
    private Contatos contato;

    public Pessoa(Long id, String nome) {
        super(nome, id);
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
    public void setContato(Contatos contato) {
        this.contato = contato;
    }

    public Contatos getContato() {
        return contato;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void addEndereco(Endereco e) {
        if (e != null) {
            e.setPessoa(this);
            this.enderecos.add(e);
        }
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoSanguineo=" + tipoSanguineo.getNome() +
                ", contato=" + contato.getNome() +
                ", enderecos=" + enderecos +
                '}';
    }
}
