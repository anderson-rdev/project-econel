package com.management.service;

import com.management.DTOs.*;
import com.management.enums.TipoEndereco;
import com.management.enums.TipoSanguineo;
import com.management.exception.ResourceNotFoundException;
import com.management.model.TipoContato;
import com.management.repository.PessoaRepository;
import com.management.repository.TipoContatoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

/**
 * Teste de integração para validar o cadastro completo de um Paciente.
 * Usa o contexto real do Spring Boot e o banco configurado.
 */
@SpringBootTest
@TestPropertySource("classpath:application.properties")
@Commit
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    @Test
    @Order(1)
    @DisplayName("Deve cadastrar um paciente com múltiplos contatos e endereços")
    void cadastrarPaciente() {
        // ================
        // 1️⃣ Arrange
        // ================
        TipoContato tipoEmail = tipoContatoRepository
                .findByDescricaoIgnoreCase("EMAIL")
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tipo de contato 'EMAIL' não encontrado. " +
                                "Certifique-se de que o DataInitializer populou os tipos padrão."
                ));

        TipoContato tipoTelefone = tipoContatoRepository
                .findByDescricaoIgnoreCase("TELEFONE")
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tipo de contato 'TELEFONE' não encontrado. " +
                                "Certifique-se de que o DataInitializer populou os tipos padrão."
                ));

        PessoaRequest request = construirPacienteRequest(tipoEmail, tipoTelefone);

        // ================
        // 2️⃣ Act
        // ================
        PessoaResponse response = pessoaService.cadastrar(request);

        // ================
        // 3️⃣ Assert
        // ================
        assertDadosBasicos(response);
        // assertContatos(response);
        assertEnderecos(response);
        assertDocumentos(response);
        assertFiliacoes(response);

        exibirResumoCompleto(response);
    }

    // ==========================
    // 🔧 MÉTODOS AUXILIARES
    // ==========================

    private PessoaRequest construirPacienteRequest(TipoContato tipoEmail, TipoContato tipoTelefone) {
        PessoaRequest request = new PessoaRequest();
        request.setNome("Lucas Pereira da Silva");
        request.setTipoSanguineo(TipoSanguineo.B_NEGATIVO);

        // Contatos
        ContatoRequest email = new ContatoRequest();
        email.setTipo(tipoEmail.getDescricao());
        email.setValor("lucas.pereira@gmail.com");

        ContatoRequest telefone = new ContatoRequest();
        telefone.setTipo(tipoTelefone.getDescricao());
        telefone.setValor("(11) 98877-6655");

        request.setContatos(Arrays.asList(email, telefone));

        // Endereços
        EnderecoDTO residencial = new EnderecoDTO();
        residencial.setRua("Rua dos Pinheiros");
        residencial.setNumero("180");
        residencial.setBairro("Pinheiros");
        residencial.setCidade("São Paulo");
        residencial.setEstado("SP");
        residencial.setCep("05422-010");
        residencial.setTipo(TipoEndereco.RESIDENCIAL);

        EnderecoDTO comercial = new EnderecoDTO();
        comercial.setRua("Av. Paulista");
        comercial.setNumero("1009");
        comercial.setBairro("Bela Vista");
        comercial.setCidade("São Paulo");
        comercial.setEstado("SP");
        comercial.setCep("01311-200");
        comercial.setTipo(TipoEndereco.COMERCIAL);

        request.setEnderecos(Arrays.asList(residencial, comercial));

        // Documentos
        DocumentosDTO documento = new DocumentosDTO();
        documento.setNumeroDocumento("485.965.720-19");
        documento.setTipoDocumento("CPF");
        request.setDocumentos(Arrays.asList(documento));

        // Filiação
        FiliacaoDTO filiacao = new FiliacaoDTO();
        filiacao.setNomePai("Rogério Pereira");
        filiacao.setNomeMae("Carla da Silva Pereira");
        request.setFiliacoes(Arrays.asList(filiacao));

        return request;
    }

    private void assertDadosBasicos(PessoaResponse response) {
        Assertions.assertNotNull(response.getId(), "O ID da pessoa deve ser gerado");
        Assertions.assertEquals("Lucas Pereira da Silva", response.getNome());
        Assertions.assertEquals(TipoSanguineo.B_NEGATIVO, response.getTipoSanguineo());
    }

    private void assertEnderecos(PessoaResponse response) {
        Assertions.assertFalse(response.getEnderecos().isEmpty(), "Deve haver ao menos um endereço");
        Assertions.assertEquals(2, response.getEnderecos().size(), "Deve conter dois endereços");
        Assertions.assertTrue(
                response.getEnderecos().stream().anyMatch(e -> e.getCidade().equals("São Paulo")),
                "Cidade deve ser São Paulo"
        );
    }

    private void assertDocumentos(PessoaResponse response) {
        Assertions.assertFalse(response.getDocumentos().isEmpty(), "Documento deve estar presente");
        DocumentosDTO doc = response.getDocumentos().get(0);
        Assertions.assertEquals("CPF", doc.getTipoDocumento());
        Assertions.assertEquals("485.965.720-19", doc.getNumeroDocumento());
    }

    private void assertFiliacoes(PessoaResponse response) {
        Assertions.assertFalse(response.getFiliacoes().isEmpty(), "Filiação deve estar presente");
        FiliacaoDTO filiacao = response.getFiliacoes().get(0);
        Assertions.assertEquals("Rogério Pereira", filiacao.getNomePai());
        Assertions.assertEquals("Carla da Silva Pereira", filiacao.getNomeMae());
    }

    private void exibirResumoCompleto(PessoaResponse pessoa) {
        System.out.println("\n=== RESUMO DO PACIENTE CADASTRADO ===");
        System.out.printf("ID: %s%n", pessoa.getId());
        System.out.printf("Nome: %s%n", pessoa.getNome());
        System.out.printf("Tipo Sanguíneo: %s%n", pessoa.getTipoSanguineo());

        if (pessoa.getContatos() != null) {
            System.out.println("Contatos:");
            pessoa.getContatos().forEach(c ->
                    System.out.printf("  %s - %s%n", c.getTipo(), c.getValor())
            );
        }

        if (pessoa.getEnderecos() != null) {
            System.out.println("Endereços:");
            pessoa.getEnderecos().forEach(e ->
                    System.out.printf("  %s: %s, %s - %s, %s/%s (CEP: %s)%n",
                            e.getTipo(), e.getRua(), e.getNumero(),
                            e.getBairro(), e.getCidade(), e.getEstado(), e.getCep())
            );
        }

        if (pessoa.getDocumentos() != null) {
            System.out.println("Documentos:");
            pessoa.getDocumentos().forEach(d ->
                    System.out.printf("  %s: %s%n", d.getTipoDocumento(), d.getNumeroDocumento())
            );
        }

        if (pessoa.getFiliacoes() != null) {
            System.out.println("Filiações:");
            pessoa.getFiliacoes().forEach(f ->
                    System.out.printf("  Pai: %s | Mãe: %s%n", f.getNomePai(), f.getNomeMae())
            );
        }

        System.out.println("===================================\n");
    }
}