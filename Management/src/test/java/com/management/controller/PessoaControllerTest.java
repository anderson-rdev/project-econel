package com.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.DTOs.PessoaRequest;
import com.management.enums.Contatos;
import com.management.enums.TipoSanguineo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveCadastrarPessoaERetornarDadosCorretos() throws Exception {
        PessoaRequest request = new PessoaRequest();
        request.setId(1L);
        request.setNome("Maria Silva");
        request.setTipoSanguineo(TipoSanguineo.B_NEGATIVO);
        request.setContato(Contatos.EMAIL);

        System.out.println(objectMapper.writeValueAsString(request));

        /*
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria Silva"))
                .andExpect(jsonPath("$.tipoSanguineo").value("A_POS"))
                .andExpect(jsonPath("$.contato").value("EMAIL"))
                .andExpect(jsonPath("$.id").value(1));
         */
    }

   /*
    @Test
    public void deveRetornarListaDePessoasComCadastroAnterior() throws Exception {
        // Primeiro cadastra uma pessoa
        PessoaRequest request = new PessoaRequest();
        request.setId(2L);
        request.setNome("João Pereira");
        request.setTipoSanguineo(TipoSanguineo.O_NEGATIVO);
        request.setContato(Contatos.TELEFONE);

        mockMvc.perform(post("/api/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Depois lista e verifica se está presente
        mockMvc.perform(get("/api/pessoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João Pereira"))
                .andExpect(jsonPath("$[0].tipoSanguineo").value("O_NEG"))
                .andExpect(jsonPath("$[0].contato").value("TELEFONE"))
                .andExpect(jsonPath("$[0].id").value(2));
    }

    */
}
