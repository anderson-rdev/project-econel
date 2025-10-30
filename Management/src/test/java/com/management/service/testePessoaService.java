package com.management.service;

import com.management.DTOs.*;
import com.management.enums.TipoContato;
import com.management.enums.TipoEndereco;
import com.management.enums.TipoSanguineo;
import com.management.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.annotation.Commit;

import java.util.Collections;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
@Commit //
public class testePessoaService {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void deveInserirPessoaNoBancoDeDados() {
        // --- Montagem do DTO de requisição ---
        PessoaRequest request = new PessoaRequest();
        request.setNome("Teste Ramos");

        request.setTipoSanguineo(TipoSanguineo.A_POSITIVO);

        // Contato
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setTipo(TipoContato.EMAIL); //findbyd na classe TipoContado que vai substituir o ENUM
        contatoDTO.setValor("ramon@email.com");
        request.setContato(contatoDTO);

        // Endereço
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Rua das Flores");
        enderecoDTO.setNumero("704");
        enderecoDTO.setBairro("Centro");
        enderecoDTO.setCidade("São Paulo");
        enderecoDTO.setEstado("SP");
        enderecoDTO.setCep("01000-000");
        enderecoDTO.setTipo(TipoEndereco.RESIDENCIAL);
        request.setEnderecos(Collections.singletonList(enderecoDTO));

        // Documento
        DocumentosDTO documentosDTO = new DocumentosDTO();
        documentosDTO.setNumeroDocumento("385.548.838-02");
        documentosDTO.setTipoDocumento("CPF");
        request.setDocumentos(Collections.singletonList(documentosDTO));

        // Filiação
        FiliacaoDTO filiacaoDTO = new FiliacaoDTO();
        filiacaoDTO.setNomePai("José Oliveira");
        filiacaoDTO.setNomeMae("Maria Oliveira");
        request.setFiliacoes(Collections.singletonList(filiacaoDTO));

        // --- Execução ---
        PessoaResponse response = pessoaService.cadastrar(request);

        // --- Exibe o resumo no console ---
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

        if (pessoa.getDocumentos() != null && !pessoa.getDocumentos().isEmpty()) {
            System.out.println("Documentos:");
            pessoa.getDocumentos().forEach(d ->
                    System.out.println("  " + d.getTipoDocumento() + ": " + d.getNumeroDocumento())
            );
        }

        if (pessoa.getFiliacoes() != null && !pessoa.getFiliacoes().isEmpty()) {
            System.out.println("Filiação:");
            pessoa.getFiliacoes().forEach(f ->
                    System.out.println("  Pai: " + f.getNomePai() + " | Mãe: " + f.getNomeMae())
            );
        }

        System.out.println("========================\n");
    }
}
