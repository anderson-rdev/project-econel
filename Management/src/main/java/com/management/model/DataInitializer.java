package com.management.model;

import com.management.repository.TipoContatoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(TipoContatoRepository tipoContatoRepository) {
        return args -> {

            // Lista base de tipos de contato que o sistema deve garantir que existam
            List<String> tiposPadrao = Arrays.asList(
                    "Email",
                    "Telefone",
                    "WhatsApp",
                    "Telegram",
                    "Instagram",
                    "Facebook",
                    "LinkedIn"
            );

            System.out.println("🔍 Verificando Tipos de Contato...");

            tiposPadrao.forEach(tipo -> {
                boolean existe = tipoContatoRepository.findByDescricaoIgnoreCase(tipo).isPresent();
                if (!existe) {
                    tipoContatoRepository.save(new TipoContato(tipo));
                    System.out.printf("✅ Tipo de contato '%s' cadastrado automaticamente.%n", tipo);
                } else {
                    System.out.printf("ℹ️ Tipo de contato '%s' já existe.%n", tipo);
                }
            });

            System.out.println("🏁 Verificação de Tipos de Contato concluída.");
        };
    }
}
