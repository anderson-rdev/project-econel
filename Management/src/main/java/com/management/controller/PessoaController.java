package com.management.controller;

import com.management.DTOs.MensagemResponse;
import com.management.DTOs.PessoaRequest;
import com.management.DTOs.PessoaResponse;
import com.management.exception.ResourceNotFoundException;
import com.management.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Para consistência, usei "Usuários" como você definiu na Tag e no RequestMapping.
@Tag(name = "Usuários", description = "Operações para gerenciamento de usuários")
@RestController
    @RequestMapping("/api/v1/usuarios")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    /**
     * Endpoint para cadastrar um novo usuário no sistema.
     *
     * Este método recebe os dados de um usuário (dados básicos, contatos e endereços)
     * e os persiste no banco de dados. A validação dos campos é automática.
     *
     * @param pessoaRequest O DTO (Data Transfer Object) contendo os dados do usuário a ser cadastrado.
     * @return PessoaResponse O DTO do usuário recém-criado, incluindo seu ID único gerado.
     * @throws org.springframework.web.bind.MethodArgumentNotValidException Em caso de falha na validação dos campos (retorna 400).
     * @throws com.management.exception.ResourceNotFoundException Em caso de tentativa de cadastro com dados únicos (ex: CPF) já existentes (retorna 409).
     */
    @Operation(summary = "Cadastrar novo usuário",
            description = "Cria um novo usuário com seus dados básicos, contatos e endereços.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PessoaResponse.class))),

            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados de entrada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class))), // Padronizado com seu DTO de erro

            @ApiResponse(responseCode = "409", description = "Conflito: Recurso já existente (ex: CPF ou E-mail já cadastrado)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class))),

            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaResponse cadastrar(@Valid @RequestBody PessoaRequest pessoaRequest) {
        return pessoaService.cadastrar(pessoaRequest);
    }

    /**
     * Busca um usuário específico pelo seu ID.
     *
     * @param id O ID (Long) do usuário a ser buscado.
     * @return ResponseEntity contendo o PessoaResponse do usuário encontrado (200 OK).
     * @throws ResourceNotFoundException Se o usuário com o ID especificado não for encontrado (retorna 404).
     */
    @Operation(summary = "Buscar usuário por ID",
            description = "Recupera os dados de um usuário específico com base em seu ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PessoaResponse.class))),

            // O 404 é tratado automaticamente pelo @ExceptionHandler
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class))),

            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        // A lógica de "não encontrado" deve estar no Service, que lança ResourceNotFoundException
        PessoaResponse response = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza os dados de um usuário existente.
     *
     * Este método recebe o ID do usuário e um DTO com os dados a serem atualizados.
     * A lógica de mesclagem dos dados (parcial ou total) é tratada pela camada de serviço.
     *
     * @param id O ID (Long) do usuário a ser atualizado.
     * @param pessoaRequest O DTO com os dados (validados) a serem atualizados.
     * @return ResponseEntity contendo o PessoaResponse do usuário atualizado (200 OK).
     * @throws ResourceNotFoundException Se o usuário com o ID especificado não for encontrado (retorna 404).
     * @throws org.springframework.web.bind.MethodArgumentNotValidException Em caso de falha na validação dos campos (retorna 400).
     */
    @Operation(summary = "Atualizar usuário por ID",
            description = "Atualiza os dados de um usuário existente. Apenas os campos fornecidos no request body serão atualizados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PessoaResponse.class))),

            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados de entrada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class))),

            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class))),

            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> alterar(
            @PathVariable Long id,
            @Valid @RequestBody PessoaRequest pessoaRequest) {

        // A lógica de buscar, mesclar dados e salvar foi movida para o service.
        // O controller apenas delega a responsabilidade.
        PessoaResponse response = pessoaService.alterar(id, pessoaRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Exclui um usuário do sistema pelo seu ID.
     *
     * Este método realiza a exclusão lógica ou física do usuário.
     * Retorna 204 No Content em caso de sucesso.
     *
     * @param id O ID (Long) do usuário a ser excluído.
     * @return ResponseEntity<Void> com status 204 No Content.
     * @throws ResourceNotFoundException Se o usuário com o ID especificado não for encontrado (retorna 404).
     */
    @Operation(summary = "Excluir usuário por ID",
            description = "Remove um usuário do sistema com base em seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso",
                    content = @Content), // Sem conteúdo no corpo da resposta

            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class))),

            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MensagemResponse.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Define o status de sucesso padrão como 204
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        // O service lança ResourceNotFoundException se o ID não existir
        pessoaService.excluir(id);

        // O padrão REST para um DELETE bem-sucedido é retornar 204 No Content
        return ResponseEntity.noContent().build();
    }

    /**
     * Handler global para exceções do tipo ResourceNotFoundException neste controller.
     * Captura a exceção e retorna uma resposta 404 Not Found padronizada.
     *
     * @param ex A exceção ResourceNotFoundException capturada.
     * @return ResponseEntity contendo a mensagem de erro e o status 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Define o status da resposta deste handler
    public MensagemResponse handleResourceNotFound(ResourceNotFoundException ex) {
        // Retorna diretamente o DTO de MensagemResponse.
        // O Spring e o @ResponseStatus cuidam de encapsular no ResponseEntity.
        return new MensagemResponse(ex.getMessage());
    }


}