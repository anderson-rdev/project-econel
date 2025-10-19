package com.management.model;

import com.management.enums.Contatos;
import com.management.enums.TipoSanguineo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestePessoaApp {

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Anderson Ramos");
        pessoa.setTipoSanguineo(TipoSanguineo.A_POSITIVO);
    }

    @Test
    void ResumoComEndereco() {
        Endereco endereco = new Endereco("Rua das Flores", "608", "Centro", "São Paulo", "SP", "01000-000", "Residencial");
        pessoa.addEndereco(endereco);

        String resumo = pessoa.imprimirResumo();

        // Exibe o resumo completo
        exibirResumoCompleto(pessoa);

        assertTrue(resumo.contains("Rua das Flores"), "Resumo deve conter o endereço adicionado");
    }

    // Método auxiliar para imprimir o resumo completo
    private void exibirResumoCompleto(Pessoa pessoa) {
        System.out.println("=== RESUMO DA PESSOA ===");
        System.out.println("ID: " + pessoa.getId());
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Tipo Sanguíneo: " + pessoa.getTipoSanguineo());

        if (pessoa.getContato() != null) {
            System.out.println("Contato: " + pessoa.getContato().getTipo() + " - " + pessoa.getContato().getValor());
        }

        if (pessoa.getEnderecos() != null && !pessoa.getEnderecos().isEmpty()) {
            System.out.println("Endereços:");
            for (Endereco e : pessoa.getEnderecos()) {
                System.out.println("  " + e.toString()); // usa o seu toString()
            }
        }
        System.out.println("========================\n");
    }

}
