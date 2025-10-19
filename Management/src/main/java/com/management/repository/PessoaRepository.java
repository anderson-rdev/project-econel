package com.management.repository;

import com.management.model.Endereco;
import com.management.model.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositório responsável pelo armazenamento e gestão de objetos do tipo Pessoa.
 *
 * <p>Esta implementação utiliza uma estrutura de dados em memória
 * ({@link HashMap}) para simular a persistência.</p>
 *
 * <p>Em uma aplicação real, esta classe poderia ser substituída por uma
 * implementação com JPA, JDBC, ou outro mecanismo de persistência.</p>
 */
@Repository
public class PessoaRepository {

    private final Map<Long, Pessoa> pessoas = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Salva uma nova pessoa no repositório.
     *
     * @param pessoa A pessoa a ser salva.
     * @return A pessoa salva com o ID atribuído.
     */
    public Pessoa salvar(Pessoa pessoa) {
        // Gera ID se ainda não existir
        if (pessoa.getId() == null) {
            pessoa.setId(idGenerator.getAndIncrement());
        }

        // Vincula cada endereço à pessoa para manter a referência bidirecional
        if (pessoa.getEnderecos() != null) {
            pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
        }

        // Salva no Map de pessoas
        pessoas.put(pessoa.getId(), pessoa);

        return pessoa;
    }


    /**
     * Busca uma pessoa pelo seu ID.
     *
     * @param id O ID da pessoa.
     * @return A pessoa encontrada ou {@code null} se não existir.
     */
    public Pessoa buscarPorId(Long id) {
        return pessoas.get(id);
    }

    /**
     * Retorna todas as pessoas armazenadas.
     *
     * @return Uma lista de pessoas.
     */
    public List<Pessoa> listarTodas() {
        return new ArrayList<>(pessoas.values());
    }

    /**
     * Atualiza os dados de uma pessoa existente.
     *
     * @param id O ID da pessoa a ser atualizada.
     * @param pessoaAtualizada A pessoa com as novas informações.
     * @return A pessoa atualizada, ou {@code null} se não existir.
     */
    public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
        Pessoa existente = pessoas.get(id);
        List<Endereco> Endereco = new ArrayList<>();
        if (existente != null) {
            existente.setNome(pessoaAtualizada.getNome());
            existente.setTipoSanguineo(pessoaAtualizada.getTipoSanguineo());
            existente.setContato(pessoaAtualizada.getContato());

            // Limpa os endereços antigos
            existente.getEnderecos().clear();

            // Adiciona os novos endereços corretamente
            for (Endereco e : pessoaAtualizada.getEnderecos()) {
                existente.addEndereco(e); // cada endereço será vinculado à pessoa
            }
        }
        return existente;
    }

    /**
     * Exclui uma pessoa pelo seu ID.
     *
     * @param id O ID da pessoa a ser removida.
     * @return {@code true} se a pessoa foi removida, {@code false} caso contrário.
     */
    public boolean excluir(Long id) {
        return pessoas.remove(id) != null;
    }
}
