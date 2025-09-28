package com.management.enums;

import com.management.Intereface.Descritivel;

public enum TipoSanguineo implements Descritivel {
    A_POSITIVO(1, "A+"),
    A_NEGATIVO(2, "A-"),
    B_POSITIVO(3, "B+"),
    B_NEGATIVO(4, "B-"),
    AB_POSITIVO(5, "AB+"),
    AB_NEGATIVO(6, "AB-"),
    O_POSITIVO(7, "O+"),
    O_NEGATIVO(8, "O-");

    private final int id;
    private final String nome;

    TipoSanguineo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNome() { return nome; }
}
