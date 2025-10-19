package com.management.service;

import com.management.model.Pessoa;
import com.management.repository.PessoaRepository;
import com.management.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável por aplicar as regras de negócio relacionadas à entidade Pessoa.
 *
 * <p>Esta classe atua como intermediária entre o controlador e o repositório, garantindo
 * a separação de responsabilidades e facilitando futuras manutenções.</p>
 */
@Service
public class PessoaService {

    private final PessoaRepository repository;

    /**
     * Construtor com injeção de dependência do repositório de pessoas.
     *
     * @param repository O repositório responsável pelo acesso a dados de Pessoa.
     */
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    /**
     * Cadastra uma nova pessoa.
     *
     * @param pessoa A pessoa a ser cadastrada.
     * @return A pessoa criada com o ID gerado.
     */
    public Pessoa cadastrar(Pessoa pessoa) {
        // Vincula cada endereço à pessoa antes de salvar
        if (pessoa.getEnderecos() != null) {
            pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
        }
        return repository.salvar(pessoa);
    }

    /**
     * Atualiza os dados de uma pessoa existente.
     *
     * @param id O ID da pessoa a ser atualizada.
     * @param pessoa A pessoa com os dados atualizados.
     * @return A pessoa atualizada.
     * @throws ResourceNotFoundException se o ID informado não existir.
     */
    public Pessoa alterar(Long id, Pessoa pessoa) {
        Pessoa existente = repository.buscarPorId(id);
        if (existente == null) {
            throw new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id);
        }
        // Vincula endereços à pessoa atualizada
        if (pessoa.getEnderecos() != null) {
            pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
        }
        return repository.atualizar(id, pessoa);
    }

    /**
     * Consulta uma pessoa pelo seu ID.
     *
     * @param id O ID da pessoa.
     * @return A pessoa encontrada.
     * @throws ResourceNotFoundException se o ID informado não existir.
     */
    public Pessoa consultar(Long id) {
        Pessoa pessoa = repository.buscarPorId(id);
        if (pessoa == null) {
            throw new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id);
        }
        return pessoa;
    }

    /**
     * Lista todas as pessoas cadastradas.
     *
     * @return Uma lista de pessoas (pode estar vazia).
     */
    public List<Pessoa> listar() {
        return repository.listarTodas();
    }

    /**
     * Exclui uma pessoa com base no seu ID.
     *
     * @param id O ID da pessoa a ser excluída.
     * @throws ResourceNotFoundException se o ID informado não existir.
     */
    public void excluir(Long id) {
        boolean removida = repository.excluir(id);
        if (!removida) {
            throw new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id);
        }
    }
}
