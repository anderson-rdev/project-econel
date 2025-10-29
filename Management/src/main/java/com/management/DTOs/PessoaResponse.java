package com.management.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.enums.TipoSanguineo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Representa os dados retornados ao consultar ou cadastrar uma pessoa")
public class PessoaResponse {

    @Schema(description = "Identificador único da pessoa", example = "1")
    private Long id;

    @Schema(description = "Nome completo da pessoa", example = "João da Silva")
    private String nome;

    @Schema(description = "Tipo sanguíneo da pessoa", example = "O_POSITIVO")
    private TipoSanguineo tipoSanguineo;

    @Schema(description = "Dados de contato da pessoa")
    private ContatoDTO contato;

    @Schema(description = "Lista de endereços associados à pessoa")
    @JsonProperty("enderecos")
    private List<EnderecoDTO> enderecos = new ArrayList<>();

    @Schema(description = "Lista de documentos da pessoa")
    @JsonProperty("documentos")
    private List<DocumentosDTO> documentos = new ArrayList<>();

    @Schema(description = "Lista de filiações da pessoa")
    @JsonProperty("filiacoes")
    private List<FiliacaoDTO> filiacoes = new ArrayList<>();

    // Construtor vazio
    public PessoaResponse() {}

    // Construtor completo
    public PessoaResponse(Long id, String nome, TipoSanguineo tipoSanguineo,
                          ContatoDTO contato, List<EnderecoDTO> enderecos,
                          List<DocumentosDTO> documentos, List<FiliacaoDTO> filiacoes) {
        this.id = id;
        this.nome = nome;
        this.tipoSanguineo = tipoSanguineo;
        this.contato = contato;
        this.enderecos = enderecos != null ? enderecos : new ArrayList<>();
        this.documentos = documentos != null ? documentos : new ArrayList<>();
        this.filiacoes = filiacoes != null ? filiacoes : new ArrayList<>();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoSanguineo getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }

    public ContatoDTO getContato() { return contato; }
    public void setContato(ContatoDTO contato) { this.contato = contato; }

    @JsonProperty("enderecos")
    public List<EnderecoDTO> getEnderecos() { return enderecos; }
    @JsonProperty("enderecos")
    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos != null ? enderecos : new ArrayList<>();
    }

    @JsonProperty("documentos")
    public List<DocumentosDTO> getDocumentos() { return documentos; }
    @JsonProperty("documentos")
    public void setDocumentos(List<DocumentosDTO> documentos) {
        this.documentos = documentos != null ? documentos : new ArrayList<>();
    }

    @JsonProperty("filiacoes")
    public List<FiliacaoDTO> getFiliacoes() { return filiacoes; }
    @JsonProperty("filiacoes")
    public void setFiliacoes(List<FiliacaoDTO> filiacoes) {
        this.filiacoes = filiacoes != null ? filiacoes : new ArrayList<>();
    }
}
