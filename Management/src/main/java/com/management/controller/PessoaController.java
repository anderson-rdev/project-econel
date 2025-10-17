package com.management.controller;

import com.management.model.Pessoa;
import com.management.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.management.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Controlador REST para gerenciar a entidade Pessoa.
 *
 * <p>Esta classe expõe uma API HTTP para realizar operações de CRUD (Criar, Ler, Atualizar, Excluir)
 * para pessoas. O endpoint base para todas as operações é {@code /api/pessoas}.</p>
 *
 * @author Anderson Ramos
 * @version 1.0
 * @since 2025-10-16
 */
@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    /**
     * Construtor que utiliza injeção de dependência para obter a instância de PessoaService.
     * <p>Este é o método preferencial para injeção de dependências no Spring, pois garante
     * que o controlador não possa ser instanciado sem suas dependências essenciais.</p>
     *
     * @param pessoaService O serviço responsável pela lógica de negócio de Pessoa.
     */
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    /**
     * Cria uma nova pessoa no sistema.
     * <p>Recebe os dados da pessoa no corpo da requisição e os persiste.</p>
     * Endpoint: {@code POST /api/pessoas}
     *
     * @param pessoa O objeto Pessoa a ser criado, contido no corpo da requisição (request body).
     * @return um {@link ResponseEntity} com a pessoa recém-criada e o status HTTP 201 (Created).
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa cadastrar(@RequestBody Pessoa pessoa) {
        return pessoaService.cadastrar(pessoa);
    }

    /**
     * Atualiza os dados de uma pessoa existente com base em seu ID.
     * Endpoint: {@code PUT /api/pessoas/{id}}
     *
     * @param id     O ID da pessoa a ser atualizada, fornecido como uma variável de caminho (path variable).
     * @param pessoa O objeto Pessoa com os dados atualizados, contido no corpo da requisição.
     * @return O objeto Pessoa após a atualização.
     * @throws ResourceNotFoundException se nenhuma pessoa for encontrada com o ID fornecido.
     */
    @PutMapping("/{id}")
    public Pessoa alterar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return pessoaService.alterar(id, pessoa);
    }

    /**
     * Busca e retorna uma pessoa específica pelo seu ID.
     * Endpoint: {@code GET /api/pessoas/{id}}
     *
     * @param id O ID da pessoa a ser consultada.
     * @return O objeto Pessoa correspondente ao ID.
     * @throws ResourceNotFoundException se nenhuma pessoa for encontrada com o ID fornecido.
     */
    @GetMapping("/{id}")
    public Pessoa consultar(@PathVariable Long id) {
        return pessoaService.consultar(id);
    }

    /**
     * Retorna uma lista com todas as pessoas cadastradas.
     * <p>Para aplicações em produção, considere implementar paginação para evitar o retorno
     * de grandes volumes de dados.</p>
     * Endpoint: {@code GET /api/pessoas}
     *
     * @return Uma {@link List} de objetos {@link Pessoa}. A lista pode estar vazia se não houver pessoas.
     */
    @GetMapping
    public List<Pessoa> listar() {
        return pessoaService.listar();
    }

    /**
     * Exclui uma pessoa do sistema com base em seu ID.
     * <p>Este método retorna uma resposta HTTP com status 204 (No Content) em caso de sucesso,
     * que é uma prática recomendada para operações de exclusão em APIs REST.</p>
     * Endpoint: {@code DELETE /api/pessoas/{id}}
     *
     * @param id O ID da pessoa a ser excluída.
     * @return Um {@link ResponseEntity} vazio com o status HTTP apropriado (204 No Content para sucesso).
     * @throws ResourceNotFoundException se nenhuma pessoa for encontrada com o ID fornecido.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        pessoaService.excluir(id);
    }
}