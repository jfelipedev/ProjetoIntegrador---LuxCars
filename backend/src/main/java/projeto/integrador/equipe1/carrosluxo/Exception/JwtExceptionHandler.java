package projeto.integrador.equipe1.carrosluxo.Exception;

import io.jsonwebtoken.JwtException;
import org.apache.logging.log4j.core.config.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JwtExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtExceptionHandler.class);

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleJwtException(JwtException ex) {
        LOGGER.error("Erro de token JWT: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token JWT inválido!");
    }
}
