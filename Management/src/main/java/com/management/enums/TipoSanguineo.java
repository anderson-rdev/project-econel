package com.management.enums;

import com.management.Intereface.Descritivel;

public enum TipoSanguineo implements Descritivel {

    A_POSITIVO(1, "A+", "A_PLUS"),
    A_NEGATIVO(2, "A-", "A_MINUS"),
    B_POSITIVO(3, "B+", "B_PLUS"),
    B_NEGATIVO(4, "B-", "B_MINUS"),
    AB_POSITIVO(5, "AB+", "AB_PLUS"),
    AB_NEGATIVO(6, "AB-", "AB_MINUS"),
    O_POSITIVO(7, "O+", "O_PLUS"),
    O_NEGATIVO(8, "O-", "O_MINUS");

    private final int id;
    private final String nome;
    private final String alias;

    TipoSanguineo(int id, String nome, String alias) {
        this.id = id;
        this.nome = nome;
        this.alias = alias;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNome() { return nome; }

    public String getAlias() { return alias; }

    public static TipoSanguineo fromAlias(String alias) {
        for (TipoSanguineo tipo : values()) {
            if (tipo.getAlias().equalsIgnoreCase(alias)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo sanguíneo inválido: " + alias);
    }
}

