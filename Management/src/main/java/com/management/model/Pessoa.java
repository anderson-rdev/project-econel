package com.management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.management.enums.TipoSanguineo;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Table(name = "pessoa")
public class Pessoa extends Dominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("endereco") // opcional, para mapear nome JSON "endereco"
    private List<Endereco> enderecos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    @OneToOne(cascade = CascadeType.ALL)
    private Contato contato;

    public Pessoa() {
        this.enderecos = new ArrayList<>();
    }

    // getters, setters
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco e) {
        if (e != null) {
            e.setPessoa(this); // vínculo do endereço com a pessoa
            this.enderecos.add(e); // adiciona à lista
        }
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    // Imprime o resumo
    public String imprimirResumo() {
        return String.format(
                "================== Informações Pessoais ==================\n" +
                        "ID:             %d\n" +
                        "Nome:           %s\n" +
                        "Tipo Sanguíneo: %s\n" +
                        "Contato:        %s\n" +
                        "Endereço:       %s\n" +
                        "==========================================================",
                id,
                nome,
                tipoSanguineo != null ? tipoSanguineo.getNome() : "Não informado",
                contato != null ? contato.getValor() : "Não informado",
                enderecos.isEmpty() ? "Nenhum endereço" : enderecos.get(0).toString()
        );

    }

    }

