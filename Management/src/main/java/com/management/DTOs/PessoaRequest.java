package com.management.DTOs;

import com.management.enums.TipoSanguineo;
import com.management.model.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Representa os dados necessários para cadastrar ou atualizar uma pessoa")
public class PessoaRequest {

    @ApiModelProperty(value = "Identificador da pessoa (utilizado para atualizações)", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome completo da pessoa", required = true, example = "João da Silva")
    @NotNull(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @ApiModelProperty(value = "Lista de endereços da pessoa")
    private List<Endereco> enderecos = new ArrayList<>();

    @ApiModelProperty(value = "Tipo sanguíneo da pessoa", required = true, example = "A_POSITIVO")
    @NotNull(message = "Tipo sanguíneo é obrigatório")
    private TipoSanguineo tipoSanguineo;

    @ApiModelProperty(value = "Dados de contato da pessoa", required = true)
    @NotNull(message = "Contato é obrigatório")
    private ContatoDTO contato;

    // Construtor padrão
    public PessoaRequest() {}

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
