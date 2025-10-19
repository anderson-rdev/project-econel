package com.management.service;

import com.management.DTOs.*;
import com.management.enums.TipoContato;
import com.management.enums.TipoEndereco;
import com.management.enums.TipoSanguineo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste unitário do fluxo de cadastro de Pessoa via PessoaService,
 * utilizando DTOs (PessoaRequest e PessoaResponse) e simulação de persistência em memória.
 */
public class TestePessoaService {

    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        pessoaService = new PessoaService();
    }

    @Test
    void deveCadastrarPessoaComEnderecoEContato() {
        // --- Montagem do DTO de requisição (PessoaRequest) ---
        PessoaRequest request = new PessoaRequest();
        request.setNome("Anderson Ramos");
        request.setTipoSanguineo(TipoSanguineo.A_POSITIVO);

        // Contato (usa TipoContato)
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipo(TipoContato.EMAIL); // enum
        contatoDTO.setValor("anderson@email.com");
        request.setContato(contatoDTO);

        // Endereço (usa TipoEndereco como enum)
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Rua das Flores");
        enderecoDTO.setNumero("608");
        enderecoDTO.setBairro("Centro");
        enderecoDTO.setCidade("São Paulo");
        enderecoDTO.setEstado("SP");
        enderecoDTO.setCep("01000-000");
        enderecoDTO.setTipo(TipoEndereco.RESIDENCIAL); // <-- agora com enum, conforme seu DTO
        request.setEnderecos(Collections.singletonList(enderecoDTO));

        // --- Execução ---
        PessoaResponse response = pessoaService.cadastrar(request);

        // --- Verificações ---
        assertNotNull(response.getId(), "O ID deve ser gerado automaticamente");
        assertEquals("Anderson Ramos", response.getNome());
        assertEquals(TipoSanguineo.A_POSITIVO, response.getTipoSanguineo());
        assertEquals("anderson@email.com", response.getContato().getValor());
        assertFalse(response.getEnderecos().isEmpty(), "A lista de endereços não deve estar vazia");
        assertEquals("Rua das Flores", response.getEnderecos().get(0).getRua());
        assertEquals(TipoEndereco.RESIDENCIAL, response.getEnderecos().get(0).getTipo());

        // --- Exibe o resumo completo no console ---
        exibirResumoCompleto(response);
    }

    // Método auxiliar para imprimir o resumo completo (simula imprimirResumo)
    private void exibirResumoCompleto(PessoaResponse pessoa) {
        System.out.println("=== RESUMO DA PESSOA ===");
        System.out.println("ID: " + pessoa.getId());
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Tipo Sanguíneo: " + pessoa.getTipoSanguineo());

        if (pessoa.getContato() != null) {
            System.out.println("Contato: " + pessoa.getContato().getTipo() + " - " + pessoa.getContato().getValor());
        }

        if (pessoa.getEnderecos() != null && !pessoa.getEnderecos().isEmpty()) {
            System.out.println("Endereços:");
            pessoa.getEnderecos().forEach(e ->
                    System.out.println("  " + e.getTipo() + ": " + e.getRua() + ", " + e.getNumero() + " - " +
                            e.getBairro() + ", " + e.getCidade() + "/" + e.getEstado() + " (CEP: " + e.getCep() + ")")
            );
        }
        System.out.println("========================\n");
    }
}
