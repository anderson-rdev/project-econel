package com.management.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.management.enums.TipoSanguineo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "Pessoas")
public class Pessoa extends Dominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    @Embedded
    private Contato contato;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Documentos> documentos = new ArrayList<>();

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Filiacao> filiacoes = new ArrayList<>();

    public Pessoa() {
        this.enderecos = new ArrayList<>();
        this.documentos = new ArrayList<>();
        this.filiacoes = new ArrayList<>();
    }

    // ==============================
    // GETTERS E SETTERS
    // ==============================
    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id = id; }

    @Override
    public String getNome() { return nome; }
    @Override
    public void setNome(String nome) { this.nome = nome; }

    public TipoSanguineo getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }

    public Contato getContato() { return contato; }
    public void setContato(Contato contato) { this.contato = contato; }

    public List<Endereco> getEnderecos() { return enderecos; }
    public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }

    public List<Documentos> getDocumentos() { return documentos; }
    public void setDocumentos(List<Documentos> documentos) { this.documentos = documentos; }

    public List<Filiacao> getFiliacoes() { return filiacoes; }
    public void setFiliacoes(List<Filiacao> filiacoes) { this.filiacoes = filiacoes; }

    // ==============================
    // MÉTODOS AUXILIARES PARA ADIÇÃO
    // ==============================
    public void addEndereco(Endereco e) {
        if (e != null) {
            e.setPessoa(this);
            this.enderecos.add(e);
        }
    }

    public void addDocumento(Documentos d) {
        if (d != null) {
            d.setPessoa(this);
            this.documentos.add(d);
        }
    }

    public void addFiliacao(Filiacao f) {
        if (f != null) {
            f.setPessoa(this);
            this.filiacoes.add(f);
        }
    }

    // ==============================
    // MÉTODO DE RESUMO
    // ==============================
    public String imprimirResumo() {
        return String.format(
                "================== Informações Pessoais ==================\n" +
                        "ID:             %d\n" +
                        "Nome:           %s\n" +
                        "Tipo Sanguíneo: %s\n" +
                        "Contato:        %s\n" +
                        "Endereço:       %s\n" +
                        "Documentos:     %s\n" +
                        "Filiações:      %s\n" +
                        "==========================================================",
                id,
                nome,
                tipoSanguineo != null ? tipoSanguineo.name() : "Não informado",
                contato != null ? contato.getValor() : "Não informado",
                enderecos.isEmpty() ? "Nenhum endereço" : enderecos.get(0).toString(),
                documentos.isEmpty() ? "Nenhum documento" : documentos.toString(),
                filiacoes.isEmpty() ? "Nenhuma filiação" : filiacoes.toString()
        );
    }
}
