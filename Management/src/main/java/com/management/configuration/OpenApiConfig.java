package com.management.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Define dinamicamente a URL base do servidor
        String baseUrl = System.getenv().getOrDefault("API_ECONEL_URL", "http://localhost:8080");

        return new OpenAPI()
                .info(new Info()
                        .title("Econel - API de Gestão de Usuários")
                        .version("v1.0.0")
                        .description("""
                    API responsável pelo gerenciamento de usuários, contatos e endereços.
                    Desenvolvida para integração dos sistemas da Econel.
                    """)
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento Econel")
                                .email("dev@econel.com.br")
                                .url("https://www.econel.com.br")))
                .servers(List.of(
                        new Server()
                                .url(baseUrl)
                                .description("Ambiente atual - definido pela variável de ambiente API_ECONEL_URL")
                ));
    }
}
