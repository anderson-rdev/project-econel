package com.management.model;

import com.management.repository.TipoContatoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(TipoContatoRepository tipoContatoRepository) {
        return args -> {

            if (tipoContatoRepository.count() == 0) {
                System.out.println("🟢 Populando Tipos de Contato iniciais...");

                tipoContatoRepository.save(new TipoContato("Email"));
                tipoContatoRepository.save(new TipoContato("Telefone"));
                tipoContatoRepository.save(new TipoContato("WhatsApp"));
                tipoContatoRepository.save(new TipoContato("Telegram"));

                System.out.println("✅ Tipos de Contato inseridos com sucesso!");
            } else {
                System.out.println("ℹ️ Tipos de Contato já existentes — inicialização ignorada.");
            }
        };
    }
}

