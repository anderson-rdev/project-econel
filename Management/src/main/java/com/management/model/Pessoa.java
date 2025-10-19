package com.management.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.management.enums.TipoSanguineo;
import java.util.ArrayList;
import java.util.List;
// Removido @JsonProperty("endereco") - não é necessário
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
import jakarta.persistence.Entity; // IMPORTANTE: Faltava @Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity // 1. Faltava a anotação @Entity
@Table(name = "pessoa")
public class Pessoa extends Dominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // 2. CORREÇÃO PRINCIPAL: Anotação @OneToMany correta
    @OneToMany(
            mappedBy = "pessoa",       // "pessoa" é o nome do campo em Endereco.java
            cascade = CascadeType.ALL, // Salva/atualiza/deleta endereços junto com a pessoa
            fetch = FetchType.EAGER,   // Carrega os endereços junto com a pessoa
            orphanRemoval = true       // Remove endereços do banco se forem removidos da lista
    )
    private List<Endereco> endereco = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    @OneToOne(cascade = CascadeType.ALL)
    private Contato contato;

    public Pessoa() {
        this.endereco = new ArrayList<>();
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
        return endereco;
    }

    // 3. Você precisa de um setEnderecos para o Jackson funcionar
    public void setEnderecos(List<Endereco> enderecos) {
        this.endereco = enderecos;
    }

    // Este método está ótimo para manter a consistência
    public void addEndereco(Endereco e) {
        if (e != null) {
            e.setPessoa(this); // vínculo do endereço com a pessoa
            this.endereco.add(e); // adiciona à lista
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

    // ... (método imprimirResumo) ...
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
                endereco.isEmpty() ? "Nenhum endereço" : endereco.get(0).toString()
        );
    }
}