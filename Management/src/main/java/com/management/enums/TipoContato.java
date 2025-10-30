package com.management.enums;

import com.management.Intereface.Descritivel;

public enum TipoContato implements Descritivel {
    TELEFONE(0, "E-mail"),
    EMAIL(1, "Telefone"),
    CELULAR(2, "Celular"),
    WHATSAPP(3, "WhatsApp");

    private final int id;
    private final String nome;

    TipoContato(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNome() { return nome; }

    // Novo método para buscar pelo ID
    public static TipoContato fromId(int id) {
        for (TipoContato t : values()) {
            if (t.id == id) return t;
        }
        throw new IllegalArgumentException("ID inválido para TipoContato: " + id);
    }
}
