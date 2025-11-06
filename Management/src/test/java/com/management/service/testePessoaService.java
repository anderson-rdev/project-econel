package com.management.service;

import com.management.DTOs.*;
import com.management.enums.TipoEndereco;
import com.management.enums.TipoSanguineo;
import com.management.model.TipoContato;
import com.management.repository.PessoaRepository;
import com.management.repository.TipoContatoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
@Commit
public class testePessoaService {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    @Test
    void deveInserirPessoaNoBancoDeDados() {
        // --- Pré-condição: garantir que o tipo de contato exista ---
        TipoContato tipoEmail = tipoContatoRepository.findByDescricaoIgnoreCase("E-MAIL")
                .orElseGet(() -> tipoContatoRepository.save(new TipoContato("E-MAIL")));

        // --- Montagem do DTO de requisição ---
        PessoaRequest request = new PessoaRequest();
        request.setNome("Reginaldo de Oliveira");
        request.setTipoSanguineo(TipoSanguineo.A_POSITIVO);

       // Contato (usando o DTO correto para POST)
        ContatoRequest contatoRequest = new ContatoRequest();
        contatoRequest.setTipo(tipoEmail.getDescricao()); // "E-MAIL"
        contatoRequest.setValor("reginaldo@email.com");
        request.setContato(contatoRequest);

        // Endereço
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Rua das Flores");
        enderecoDTO.setNumero("710");
        enderecoDTO.setBairro("Zona Norte");
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
