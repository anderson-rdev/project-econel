package com.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando um recurso (entidade, registro, etc.)
 * não é encontrado no sistema.
 *
 * <p>Ao ser lançada, o Spring automaticamente retornará o status HTTP 404 (Not Found).</p>
 *
 * <p>Exemplo de uso:</p>
 * <pre>
 *     Pessoa pessoa = repository.findById(id)
 *          .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));
 * </pre>
 *
 * @author Anderson
 * @since 2025-10-16
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Cria uma nova exceção com a mensagem informada.
     *
     * @param message mensagem descritiva do erro.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Cria uma nova exceção com mensagem e causa raiz.
     *
     * @param message mensagem descritiva do erro.
     * @param cause causa raiz da exceção.
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
