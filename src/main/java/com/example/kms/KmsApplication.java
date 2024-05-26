package com.example.kms;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(
        servers = {

                @Server(
                        description = "Prod ENV",
                        url = "https://kmsadmin-production.up.railway.app"
                ),
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        }
)

@SpringBootApplication
public class KmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(KmsApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("2.2.0") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Key Management System API").version(appVersion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
