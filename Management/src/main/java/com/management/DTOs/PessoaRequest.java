package com.management.DTOs;

import com.management.enums.Contatos;
import com.management.enums.TipoSanguineo;
import com.management.model.Endereco;

import java.util.ArrayList;
import java.util.List;

public class PessoaRequest {
    private Long id;
    private String nome;
    private List<Endereco> enderecos = new ArrayList<>();
    private TipoSanguineo tipoSanguineo;
    private Contatos contato;

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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public Contatos getContato() {
        return contato;
    }

    public void setContato(Contatos contato) {
        this.contato = contato;
    }
}

