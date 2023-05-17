package br.com.neosaude.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("NEO SAUDE® API")
                        .description("API Rest da aplicação NEO SAUDE®, contendo todos os endpoints necessários para a operação do aplicativo, incluindo features necessárias para a prescrição online de medicamentos")
                        .version("V 1.0")
                        .contact(new Contact()
                                .name("Luís Barros")
                                .email("luisrochabarros@hotmail.com")));
    }
}
