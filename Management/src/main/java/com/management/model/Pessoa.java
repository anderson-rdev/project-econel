package com.management.model;

import com.management.enums.TipoSanguineo;
import java.util.ArrayList;
import java.util.List;

public class Pessoa extends Dominio {
    private Long id;
    private String nome;
    private List<Endereco> enderecos = new ArrayList<>();
    private TipoSanguineo tipoSanguineo;
    private Contato contato;

    public Pessoa(Long id, String nome) {
        super(nome, id);
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
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

    public void addEndereco(Endereco e) {
        if (e != null) {
            e.setPessoa(this);
            this.enderecos.add(e);
        }
    }

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
