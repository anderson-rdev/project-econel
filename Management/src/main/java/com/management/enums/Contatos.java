package com.management.enums;

import com.management.Intereface.Descritivel;

public enum Contatos implements Descritivel {
    EMAIL(1, "E-mail"),
    TELEFONE(2, "Telefone"),
    CELULAR(3, "Celular"),
    WHATSAPP(4, "WhatsApp");

    private final int id;
    private final String nome;

    Contatos(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNome() { return nome; }
}