package projeto.integrador.equipe1.carrosluxo.Configuraton;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfiguration {
    Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Bean
    public OpenAPI SwaggerConfiguration() {
        logger.trace("Retornando as configurações do swagger...");
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .version("Sprint 3")
                        .title("CarLux API")
                        .description("Aluguel de carros de luxo."));
    }
}