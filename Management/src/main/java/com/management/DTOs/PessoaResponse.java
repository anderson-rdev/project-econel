package com.management.DTOs;

import com.management.enums.TipoSanguineo;
import com.management.model.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Representa a resposta da API com os dados de uma pessoa")
public class PessoaResponse {

    @ApiModelProperty(value = "Identificador único da pessoa", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome completo da pessoa", example = "Maria Silva")
    private String nome;

    @ApiModelProperty(value = "Lista de endereços da pessoa")
    private List<Endereco> enderecos = new ArrayList<>();

    @ApiModelProperty(value = "Tipo sanguíneo da pessoa", example = "O_POSITIVO")
    private TipoSanguineo tipoSanguineo;

    @ApiModelProperty(value = "Informações de contato da pessoa")
    private ContatoDTO contato;

    // Construtor padrão
    public PessoaResponse() {}

    // Construtor completo (opcional)
    public PessoaResponse(Long id, String nome, List<Endereco> enderecos, TipoSanguineo tipoSanguineo, ContatoDTO contato) {
        this.id = id;
        this.nome = nome;
        this.enderecos = enderecos != null ? enderecos : new ArrayList<>();
        this.tipoSanguineo = tipoSanguineo;
        this.contato = contato;
    }

    // Getters e Setters
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
        this.enderecos = enderecos != null ? enderecos : new ArrayList<>();
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public ContatoDTO getContato() {
        return contato;
    }

    public void setContato(ContatoDTO contato) {
        this.contato = contato;
    }
}
