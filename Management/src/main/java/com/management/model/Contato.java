package com.management.model;

import com.management.enums.Contatos;

public class Contato {
    private Contatos tipo;
    private String valor;

    // Define limite por tipo
    private static final int LIMITE_TEL = 15;
    private static final int LIMITE_EMAIL = 50;

    public Contato(Contatos tipo, String valor) {
        if (!validar(tipo, valor)) {
            throw new IllegalArgumentException("Contato inválido para o tipo: " + tipo.getNome());
        }
        this.tipo = tipo;
        this.valor = valor;
    }

    private boolean validar(Contatos tipo, String valor) {
        if (valor == null) return false;

        switch (tipo) {
            case TELEFONE:
            case CELULAR:
            case WHATSAPP:
                return valor.length() <= LIMITE_TEL && valor.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}");
            case EMAIL:
                return valor.length() <= LIMITE_EMAIL && valor.contains("@");
            default:
                return false;
        }
    }

    public Contatos getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
