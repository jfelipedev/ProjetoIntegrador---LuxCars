package projeto.integrador.equipe1.carrosluxo.Configuraton;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI SwaggerConfiguration() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("CarLux API").description(
                        "Aluguel de carros de luxo."));

    }
}