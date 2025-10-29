package com.management.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.management.enums.TipoSanguineo;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "Pessoas")
public class Pessoa extends Dominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(
            mappedBy = "pessoa",  // Assumindo que Endereco tem um campo "Pessoas"
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,  // Ou LAZY para performance
            orphanRemoval = true
    )
    private List<Endereco> enderecos = new ArrayList<>();  // Renomeado para "Enderecos" (plural)

    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    @Embedded  // Adicionado: Embute Contato na tabela pessoa
    private Contato contato;

    public Pessoa() {
        this.enderecos = new ArrayList<>();
    }

    // Getters e setters (atualizados)
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

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void addEndereco(Endereco e) {
        if (e != null) {
            e.setPessoa(this);
            this.enderecos.add(e);
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

    // Método imprimirResumo (atualizado para usar "enderecos")
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
                tipoSanguineo != null ? tipoSanguineo.name() : "Não informado",  // Usando name() para enum
                contato != null ? contato.getValor() : "Não informado",
                enderecos.isEmpty() ? "Nenhum endereço" : enderecos.get(0).toString()
        );
    }
}
