package com.management.model;

public class TesteCadastroDePessoa {

    public static void main(String[] args) {
        // Criando instâncias
        Documentos doc = new Documentos(1L, "Anderson", "123456789", "RG");
        Filiacao filiacao = new Filiacao(1L, "Anderson", "José", "Maria");

        // Executando testes
        exibirDocumento(doc);
        exibirFiliacao(filiacao);
    }

    private static void exibirDocumento(Documentos doc) {
        System.out.println("=== Dados do Documento ===");
        System.out.println("ID: " + doc.getId());
        System.out.println("Nome: " + doc.getNome());
        System.out.println("Tipo: " + doc.getTipoDocumento());
        System.out.println("Número: " + doc.getNumeroDocumento());
        System.out.println();
    }

    private static void exibirFiliacao(Filiacao filiacao) {
        System.out.println("=== Dados de Filiação ===");
        System.out.println("ID: " + filiacao.getId());
        System.out.println("Nome: " + filiacao.getNome());
        System.out.println("Pai: " + filiacao.getNomePai());
        System.out.println("Mãe: " + filiacao.getNomeMae());
        System.out.println();
    }
}
