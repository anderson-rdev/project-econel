package com.management.service;

import com.management.DTOs.*;
import com.management.enums.TipoContato;
import com.management.enums.TipoEndereco;
import com.management.enums.TipoSanguineo;
import com.management.repository.PessoaRepository;
import com.management.model.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 🚀 Carrega o contexto completo do Spring Boot e conecta ao banco configurado
@TestPropertySource("classpath:application.properties")
public class testePessoaService {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void deveInserirPessoaNoBancoDeDados() {
        // --- Montagem do DTO de requisição ---
        PessoaRequest request = new PessoaRequest();
        request.setNome("Anderson Ramos");

        TipoSanguineo tipo = Arrays.stream(TipoSanguineo.values())
                .filter(t -> t.getNome().equals("A+"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo inválido"));

        request.setTipoSanguineo(tipo);


        // Contato
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipo(TipoContato.EMAIL);
        contatoDTO.setValor("anderson@email.com");
        request.setContato(contatoDTO);

        // Endereço
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Rua das Flores");
        enderecoDTO.setNumero("608");
        enderecoDTO.setBairro("Centro");
        enderecoDTO.setCidade("São Paulo");
        enderecoDTO.setEstado("SP");
        enderecoDTO.setCep("01000-000");
        enderecoDTO.setTipo(TipoEndereco.RESIDENCIAL);
        request.setEnderecos(Collections.singletonList(enderecoDTO));

        // Documentos
        DocumentosDTO documentosDTO = new DocumentosDTO();
        documentosDTO.setNumeroDocumento("12345678900");
        documentosDTO.setTipoDocumento("CPF");
        request.setDocumentos(Collections.singletonList(documentosDTO));

        // Filiação
        FiliacaoDTO filiacaoDTO = new FiliacaoDTO();
        filiacaoDTO.setNomePai("José Oliveira");
        filiacaoDTO.setNomeMae("Maria Oliveira");
        request.setFiliacoes(Collections.singletonList(filiacaoDTO));

        // --- Execução ---
        PessoaResponse response = pessoaService.cadastrar(request);

        // --- Validação ---
        assertNotNull(response.getId(), "O ID deve ser gerado automaticamente");
        assertEquals("Reginaldo Oliveira", response.getNome());
        assertEquals(TipoSanguineo.A_POSITIVO, response.getTipoSanguineo());

        // Documentos e Filiação
        assertFalse(response.getDocumentos().isEmpty(), "Documentos devem ser preenchidos");
        assertFalse(response.getFiliacoes().isEmpty(), "Filiacao deve ser preenchida");

        // --- Verifica se realmente foi salvo no banco ---
        Pessoa pessoaSalva = pessoaRepository.findById(response.getId())
                .orElseThrow(() -> new AssertionError("Pessoa não encontrada no banco!"));

        assertEquals("Reginaldo Oliveira", pessoaSalva.getNome());
        assertEquals(TipoSanguineo.A_POSITIVO, pessoaSalva.getTipoSanguineo());

        exibirResumoCompleto(response);
    }

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
