package com.management.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.management.enums.TipoSanguineo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa os dados necessários para cadastrar ou atualizar uma Pessoa.
 * Inclui informações pessoais, contatos, endereços, documentos e filiação.
 */
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

    // ============================================================
    // 🔹 Contatos (permite múltiplos)
    // ============================================================
    @Schema(description = "Lista de contatos associados à pessoa (e.g., e-mail, telefone, WhatsApp)")
    @Valid
    @NotEmpty(message = "A pessoa deve ter ao menos um contato")
    @JsonProperty("contatos")
    private List<ContatoRequest> contatos = new ArrayList<>();

    // ============================================================
    // 🔹 Endereços
    // ============================================================
    @Schema(description = "Lista de endereços associados à pessoa")
    @Valid
    @Size(max = 5, message = "A pessoa pode ter no máximo 5 endereços cadastrados")
    @JsonProperty("enderecos")
    private List<EnderecoDTO> enderecos = new ArrayList<>();

    // ============================================================
    // 🔹 Documentos
    // ============================================================
    @Schema(description = "Lista de documentos da pessoa (CPF, RG, CRM, etc.)")
    @Valid
    @JsonProperty("documentos")
    private List<DocumentosDTO> documentos = new ArrayList<>();

    // ============================================================
    // 🔹 Filiações
    // ============================================================
    @Schema(description = "Lista de filiações da pessoa (pai e mãe)")
    @Valid
    @JsonProperty("filiacoes")
    private List<FiliacaoDTO> filiacoes = new ArrayList<>();

    // ============================================================
    // 🔧 Construtores
    // ============================================================
    public PessoaRequest() {}

    // ============================================================
    // ⚙️ Getters e Setters
    // ============================================================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoSanguineo getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }

    @JsonProperty("contatos")
    public List<ContatoRequest> getContatos() { return contatos; }
    @JsonProperty("contatos")
    public void setContatos(List<ContatoRequest> contatos) {
        this.contatos = contatos != null ? contatos : new ArrayList<>();
    }

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
