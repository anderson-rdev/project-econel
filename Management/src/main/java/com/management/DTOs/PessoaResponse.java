package com.management.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.enums.TipoSanguineo;
import java.util.ArrayList;
import java.util.List;

public class PessoaResponse {

    private Long id;
    private String nome;
    private TipoSanguineo tipoSanguineo;
    private ContatoDTO contato;
    @JsonProperty("endereco")
    private List<EnderecoDTO> endereco = new ArrayList<>();

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoSanguineo getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }

    public ContatoDTO getContato() { return contato; }
    public void setContato(ContatoDTO contato) { this.contato = contato; }

    @JsonProperty("endereco")
    public List<EnderecoDTO> getEnderecos() { return endereco; }

    @JsonProperty("endereco")
    public void setEnderecos(List<EnderecoDTO> endereco) {
        this.endereco = endereco != null ? endereco : new ArrayList<>();
    }
}
