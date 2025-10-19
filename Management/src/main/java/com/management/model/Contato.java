package com.management.model;

import com.management.enums.TipoContato;
import java.util.Objects;
import java.util.regex.Pattern;

public class Contato {
    private TipoContato tipo;
    private String valor;

    // --- Constantes de Limite ---
    private static final int LIMITE_TEL_CEL = 15;
    private static final int LIMITE_EMAIL = 50;

    // --- Padrões de Validação (Regex) ---
    private static final String REGEX_TELEFONE = "^(\\+\\d{1,3}\\s?)?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    // --- Construtor vazio (necessário para frameworks) ---
    public Contato() {
        // Construtor padrão
    }

    // --- Construtor com validação ---
    public Contato(TipoContato tipo, String valor) {
        if (!validar(tipo, valor)) {
            throw new IllegalArgumentException("Contato inválido para o tipo: " + tipo.name() + " ou formato incorreto.");
        }
        this.tipo = tipo;
        this.valor = valor.trim();
    }

    // --- Getters e Setters ---
    public TipoContato getTipo() {
        return tipo;
    }

    public void setTipo(TipoContato tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    // --- Métodos de validação ---
    private boolean validar(TipoContato tipo, String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }

        String valorLimpo = valor.trim();

        switch (tipo) {
            case TELEFONE:
            case CELULAR:
            case WHATSAPP:
                return valorLimpo.length() <= LIMITE_TEL_CEL &&
                        Pattern.matches(REGEX_TELEFONE, valorLimpo);

            case EMAIL:
                return valorLimpo.length() <= LIMITE_EMAIL &&
                        Pattern.matches(REGEX_EMAIL, valorLimpo);

            default:
                return false;
        }
    }

    // --- Equals e HashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return tipo == contato.tipo && Objects.equals(valor, contato.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, valor);
    }

    // --- ToString ---
    @Override
    public String toString() {
        return tipo.getNome() + ": " + valor;
    }
}
