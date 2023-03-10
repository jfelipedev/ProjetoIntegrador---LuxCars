package projeto.integrador.equipe1.carrosluxo.Configuraton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    Logger logger = LoggerFactory.getLogger(PasswordEncoder.class);

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        logger.trace("Retornando CryptPasswordEncoder...");
        return new BCryptPasswordEncoder();
    }
}
