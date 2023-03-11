package projeto.integrador.equipe1.carrosluxo.Configuraton;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Server> servers= new ArrayList();
        Server server1 = new Server();
        Server server2 = new Server();
        Server server3 = new Server();
        Server server4 = new Server();
        server1.setUrl("https://api.carlux.viniciusofagundes.com.br");
        server2.setUrl("http://localhost");
        server3.setUrl("http://localhost:8080");
        server4.setUrl("https://back.viniciusofagundes.com.br");
        servers.add(server1);
        servers.add(server2);
        servers.add(server3);
        servers.add(server4);
        return new OpenAPI()
                .components(new Components())
                .servers(servers)
                .info(new Info()
                        .version("Sprint 2")
                        .title("CarLux API")
                        .description("Aluguel de carros de luxo."));
    }
}