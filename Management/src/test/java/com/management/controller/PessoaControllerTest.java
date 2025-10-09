package com.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.DTOs.PessoaRequest;
import com.management.DTOs.ContatoDTO;
import com.management.enums.Contatos;
import com.management.enums.TipoSanguineo;
import com.management.model.Documentos;
import com.management.model.Filiacao;
import com.management.service.ManagementApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PessoaController.class)
@ContextConfiguration(classes = ManagementApplication.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void cadastrarPessoa_deveRetornarStatusCreated() throws Exception {
        PessoaRequest request = new PessoaRequest();
        request.setId(1L);
        request.setNome("Maria Silva");
        request.setTipoSanguineo(TipoSanguineo.B_NEGATIVO);

        Documentos doc = new Documentos(1L, "Anderson", "123456789", "RG");
        Filiacao filiacao = new Filiacao(1L, "Anderson", "José", "Maria");

        ContatoDTO contatoDTO = new ContatoDTO(Contatos.CELULAR, "(11) 93930-2000");
        request.setContato(contatoDTO);

        // Converte o request para JSON
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/pessoas")  // ajuste a URL para seu endpoint real
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated()); // supondo que o endpoint retorna 201
    }
}
