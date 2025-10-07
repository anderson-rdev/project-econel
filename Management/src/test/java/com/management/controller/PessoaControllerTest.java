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

    }


}
