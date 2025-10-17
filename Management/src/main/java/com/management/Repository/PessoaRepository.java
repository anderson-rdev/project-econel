package com.management.Repository;

import com.management.model.Pessoa;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class PessoaRepository {
    private final Map<Long, Pessoa> pessoas = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Pessoa salvar(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            pessoa = new Pessoa(idGenerator.getAndIncrement(), pessoa.getNome());
        }
        pessoas.put(pessoa.getId(), pessoa);
        return pessoa;
    }

    public Pessoa buscarPorId(Long id) {
        return pessoas.get(id);
    }

    public List<Pessoa> listarTodas() {
        return new ArrayList<>(pessoas.values());
    }

    public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
        Pessoa existente = pessoas.get(id);
        if (existente != null) {
            existente.setTipoSanguineo(pessoaAtualizada.getTipoSanguineo());
            existente.setContato(pessoaAtualizada.getContato());
            existente.getEnderecos().clear();
            existente.getEnderecos().addAll(pessoaAtualizada.getEnderecos());
        }
        return existente;
    }

    public boolean excluir(Long id) {
        return pessoas.remove(id) != null;
    }
}
