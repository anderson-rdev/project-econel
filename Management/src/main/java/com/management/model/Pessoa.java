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

    public Pessoa(String nome, Long id, TipoSanguineo tipoSanguineo, Contatos contato) {
        super(nome, id);
        this.tipoSanguineo = tipoSanguineo;
        this.contato = contato;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public List<Endereco> getEnderecos() { return enderecos; }
    public TipoSanguineo getTipoSanguineo() { return tipoSanguineo; }
    public Contatos getContato() { return contato; }

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
