package com.management.service;

import com.management.DTOs.PessoaRequest;
import com.management.DTOs.PessoaResponse;
import com.management.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final List<Pessoa> pessoas = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGenerator = new AtomicLong(0);

    public PessoaResponse cadastrarPessoa(PessoaRequest request) {
        Pessoa pessoa = mapToModel(request);
        pessoas.add(pessoa);
        return mapToResponse(pessoa);
    }

    public List<PessoaResponse> listarPessoas() {
        synchronized (pessoas) {
            return pessoas.stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }
    }

    private Pessoa mapToModel(PessoaRequest req) {
        Pessoa p = new Pessoa(idGenerator.incrementAndGet(), req.getNome());
        // mapear outros campos conforme necessário
        return p;
    }

    private PessoaResponse mapToResponse(Pessoa p) {
        PessoaResponse resp = new PessoaResponse();
        resp.setId(p.getId());
        resp.setNome(p.getNome());
        resp.setEnderecos(p.getEnderecos());
        // outros campos mapeados
        return resp;
    }
}
