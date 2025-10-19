package com.management.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.enums.TipoSanguineo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Representa os dados necessários para cadastrar ou atualizar uma pessoa")
public class PessoaRequest {

    @Schema(description = "Identificador único da pessoa (usado apenas para atualização)", example = "1")
    private Long id;

    @Schema(description = "Nome completo da pessoa", example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Schema(description = "Tipo sanguíneo da pessoa", example = "O_POSITIVO", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O tipo sanguíneo é obrigatório")
    private TipoSanguineo tipoSanguineo;

    @Schema(description = "Dados de contato da pessoa", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Os dados de contato são obrigatórios")
    @Valid
    private ContatoDTO contato;

    @Schema(description = "Lista de endereços associados à pessoa")
    @Valid
    @Size(max = 5, message = "A pessoa pode ter no máximo 5 endereços cadastrados")
    @JsonProperty("enderecos")
    private List<EnderecoDTO> endereco = new ArrayList<>();

    public PessoaRequest() {}

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
    public List<EnderecoDTO> getEnderecos() { return endereco; }
    @JsonProperty("enderecos")
    public void setEnderecos(List<EnderecoDTO> endereco) { this.endereco = endereco != null ? endereco : new ArrayList<>(); }
}
