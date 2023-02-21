package projeto.integrador.equipe1.carrosluxo.Exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    Logger logger = LoggerFactory.getLogger(GlobalException.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> processingErrorNotFound(ResourceNotFoundException ex){
        logger.trace(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> processingErrorBadRequest(BadRequestException ex){
        logger.trace(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> processingErrorForbidden(ForbiddenException ex){
        logger.trace(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<String> InternalServerErrorException(InternalServerErrorException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro Interno do servidor!");
    }
}