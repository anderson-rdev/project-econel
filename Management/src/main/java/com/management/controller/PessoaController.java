package com.management.controller;

import com.management.DTOs.PessoaRequest;
import com.management.DTOs.PessoaResponse;
import com.management.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para operações relacionadas à entidade Pessoa.
 */
@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    /**
     * Cadastra uma nova pessoa no sistema.
     *
     * @param pessoaRequest os dados da pessoa a serem cadastrados
     * @return PessoaResponse com os dados salvos
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaResponse cadastrar(@RequestBody PessoaRequest pessoaRequest) {
        return pessoaService.cadastrar(pessoaRequest);
    }

    /**
     * Busca uma pessoa pelo ID.
     *
     * @param id identificador da pessoa
     * @return PessoaResponse com os dados encontrados
     */
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        PessoaResponse response = pessoaService.buscarPorId(id);
        return response != null
                ? ResponseEntity.ok(response)
                : ResponseEntity.notFound().build();
    }
}
