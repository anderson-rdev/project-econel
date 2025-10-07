package com.management.controller;

import com.management.DTOs.PessoaRequest;
import com.management.DTOs.PessoaResponse;
import com.management.model.Pessoa;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/econel/api/pessoas")
public class PessoaController {

    private final List<Pessoa> pessoas = new ArrayList<>();

    // Mapeia PessoaRequest para Pessoa (modelo)
    @org.jetbrains.annotations.NotNull
    private Pessoa mapToModel(@NotNull PessoaRequest req) {
             Pessoa p = new Pessoa(1l,"Anderson Ramos");
             p.setId(req.getId());
             p.setNome(req.getNome());
        return p;
    }

    // Mapeia Pessoa para PessoaResponse
    private PessoaResponse mapToResponse(Pessoa p) {
        PessoaResponse resp = new PessoaResponse();
        resp.setId(p.getId());
        resp.setNome(p.getNome());
        resp.setEnderecos(p.getEnderecos());
        resp.setTipoSanguineo(p.getTipoSanguineo());
        resp.setContato(p.getContato());
        return resp;
    }

    @PostMapping
    public PessoaResponse cadastrarPessoa(@RequestBody PessoaRequest pessoaRequest) {
        Pessoa pessoa = mapToModel(pessoaRequest);
        pessoas.add(pessoa);
        return mapToResponse(pessoa);
    }

    @GetMapping
    public List<PessoaResponse> listarPessoas() {
        return pessoas.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
