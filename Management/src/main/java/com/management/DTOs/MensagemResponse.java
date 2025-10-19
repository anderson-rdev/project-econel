package com.management.DTOs;

import java.time.LocalDateTime;

// DTO de resposta para operações
public record MensagemResponse(String mensagem, LocalDateTime timestamp) {
    public MensagemResponse(String mensagem) {
        this(mensagem, LocalDateTime.now());
    }
}
