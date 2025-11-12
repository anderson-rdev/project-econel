package com.management.service;

import com.management.DTOs.*;
import com.management.enums.TipoEndereco;
import com.management.enums.TipoSanguineo;
import com.management.exception.ResourceNotFoundException;
import com.management.model.TipoContato;
import com.management.repository.TipoContatoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
@Commit
class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    @Test
    @DisplayName("Cadastrar paciente e gravar no banco de dados")
    void CadastrarPessoaComSucesso() {

        // Busca tipos de contato (EMAIL e TELEFONE)
        TipoContato tipoEmail = tipoContatoRepository
                .findByDescricaoIgnoreCase("EMAIL")
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de contato 'EMAIL' não encontrado."));

        TipoContato tipoTelefone = tipoContatoRepository
                .findByDescricaoIgnoreCase("TELEFONE")
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de contato 'TELEFONE' não encontrado."));

        // Monta o objeto de requisição
        PessoaRequest request = new PessoaRequest();
        request.setNome("Teste Pereira da Silva");
        request.setTipoSanguineo(TipoSanguineo.B_NEGATIVO);

        // Contatos
        ContatoRequest email = new ContatoRequest();
        email.setTipo(tipoEmail.getDescricao());
        email.setValor("teste.pereira@gmail.com");

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

        // Documento
        DocumentosDTO documento = new DocumentosDTO();
        documento.setTipoDocumento("CPF");
        documento.setNumeroDocumento("666.965.720-19");
        request.setDocumentos(Arrays.asList(documento));

        // Filiação
        FiliacaoDTO filiacao = new FiliacaoDTO();
        filiacao.setNomePai("Teste Pereira");
        filiacao.setNomeMae("Teste da Silva Pereira");
        request.setFiliacoes(Arrays.asList(filiacao));

        // Executa o cadastro — grava no banco
        pessoaService.cadastrar(request);
    }
}
