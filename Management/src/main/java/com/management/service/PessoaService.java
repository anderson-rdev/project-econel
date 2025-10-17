package com.management.service;

import com.management.model.Pessoa;
import com.management.Repository.PessoaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository repository = new PessoaRepository();

    public Pessoa cadastrar(Pessoa pessoa) {
        return repository.salvar(pessoa);
    }

    public Pessoa alterar(Long id, Pessoa pessoa) {
        return repository.atualizar(id, pessoa);
    }

    public Pessoa consultar(Long id) {
        return repository.buscarPorId(id);
    }

    public List<Pessoa> listar() {
        return repository.listarTodas();
    }

    public boolean excluir(Long id) {
        return repository.excluir(id);
    }

}
