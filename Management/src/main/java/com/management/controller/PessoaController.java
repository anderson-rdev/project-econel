package com.management.controller;

import com.management.DTOs.PessoaRequest;
import com.management.DTOs.PessoaResponse;
import com.management.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    // =======================================
    // Criar (POST) - já implementado no service
    // =======================================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaResponse cadastrar(@RequestBody PessoaRequest pessoaRequest) {
        return pessoaService.cadastrar(pessoaRequest);
    }

    // =======================================
    // Consultar (GET) | Busca pelo Id
    // =======================================
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        PessoaResponse response = pessoaService.buscarPorId(id);
        return response != null
                ? ResponseEntity.ok(response)
                : ResponseEntity.notFound().build();
    }

    // =======================================
    // Alterar (PUT)
    // =======================================
    @PutMapping("/{id}")
    public  ResponseEntity<PessoaResponse> alterar(
            @PathVariable Long id,
            @RequestBody @Validated PessoaRequest pessoaRequest){

        // Recupera a pessoa existente
        PessoaResponse pessoaExistente = pessoaService.buscarPorId(id);

        // Atualizada os dados do cadastro da pessoa
        pessoaExistente.setNome(pessoaRequest.getNome() != null ? pessoaRequest.getNome() : pessoaExistente.getNome());
        pessoaExistente.setTipoSanguineo(pessoaRequest.getTipoSanguineo() != null ? pessoaRequest.getTipoSanguineo() : pessoaExistente.getTipoSanguineo());
        pessoaExistente.setEnderecos(pessoaRequest.getEnderecos() != null ? pessoaRequest.getEnderecos() : pessoaExistente.getEnderecos());
        pessoaExistente.setContato(pessoaRequest.getContato() != null ? pessoaRequest.getContato() : pessoaExistente.getContato() );

        // Atualiza a pessoa no service
        PessoaResponse response = pessoaService.alterar(id, pessoaRequest);
        return ResponseEntity.ok(response);

    }

}
