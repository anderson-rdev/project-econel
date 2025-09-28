package com.management.controller;

import com.management.model.Pessoa;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    // Simulando um "banco de dados" na memória
    private final List<Pessoa> pessoas = new ArrayList<>();

    // Cadastrar pessoa
    @PostMapping
    public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa) {
        pessoas.add(pessoa);
        return pessoa;
    }

    // Listar todas as pessoas
    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoas;
    }
}
