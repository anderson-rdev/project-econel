package com.management.controller;

import com.management.DTOs.PessoaRequest;
import com.management.DTOs.PessoaResponse;
import com.management.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/econel/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    // Injeção via construtor (melhor para testes e imutabilidade)
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> cadastrarPessoa(@Valid @RequestBody PessoaRequest pessoaRequest) {
        PessoaResponse response = pessoaService.cadastrarPessoa(pessoaRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listarPessoas() {
        List<PessoaResponse> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoas);
    }
}
